using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Shared.DTOs;
using Shared.Models;
using Offer = Shared.Models.Offer;

namespace Application.LogicImplementations;

/// <summary>
/// Offer logic that implements from <see cref="IOfferDao"/> that is responsible for checking and validating data
/// </summary>
public class OfferLogic : IOfferLogic
{
    private IOfferDao offerDao;
    private IImageDao imageDao;
    private IFarmDao farmDao;

    /// <summary>
    /// Initializing the OfferLogic with the given IOfferDao
    /// </summary>
    /// <param name="offerDao"></param>
    public OfferLogic(IOfferDao offerDao, IImageDao imageDao, IFarmDao farmDao)
    {
        this.offerDao = offerDao;
        this.imageDao = imageDao;
        this.farmDao = farmDao;
    }
    
    /// <summary>
    /// Create asynchronously a Offer using the OfferCreationDto object. Call the CreateAsync method in gRPC client 
    /// </summary>
    /// <param name="dto">The object holding all the offer information</param>
    /// <returns>The created Offer object</returns>
    public async Task<Offer> CreateAsync(OfferCreationDto dto)
    {
        await ValidateData(dto);

        Offer offerToSend = new Offer
        {
            Name = dto.Name,
            Quantity = dto.Quantity,
            Unit = dto.Unit,
            Price = dto.Price,
            Delivery = dto.Delivery,
            PickUp = dto.PickUp,
            PickYourOwn = dto.PickYourOwn,
            Description = dto.Description,
            Image = new Image()
            {
                RelativeUrl = ""
            },
            FarmName = dto.FarmName
        };

        var created = await offerDao.CreateAsync(offerToSend);

        // set the absolute url for an image
        created.Image.AbsoluteUrl = imageDao.GetAbsoluteUrl(offerToSend.Image.RelativeUrl);
        
        return created;
    }

    /// <summary>
    /// Getting asynchronously all the offers through the IOfferDao
    /// </summary>
    /// <returns>A Collection of Offers</returns>
    public async Task<IEnumerable<Offer>> GetAsync()
    {
        var results = await offerDao.GetAsync();

        foreach (Offer offer in results)
        {
            offer.Image.AbsoluteUrl = imageDao.GetAbsoluteUrl(offer.Image.RelativeUrl);
        }

        return results;
    }


    /// <summary>
    /// Validating the data when creating an offer
    /// </summary>
    /// <param name="dto">The offer to be created</param>
    /// <exception cref="Exception"></exception>
    private async Task ValidateData(OfferCreationDto dto)
    {
        
        if (dto.Name.Length > 100)
        {
            throw new Exception("Offer name is too long!");
        }

        if (dto.Quantity <= 0)
        {
            throw new Exception("Quantity must be bigger than 0!");
        }

        if (dto.Price <= 0)
        {
            throw new Exception("Price must be bigger than 0!");
        }
        
        // get farm by name from dao and check if it exists
        var farm = await farmDao.GetByName(dto.FarmName);
        
        if (farm == null)
        {
            throw new Exception($"Farm {dto.FarmName} does not exist!");
        }
        
    }
}