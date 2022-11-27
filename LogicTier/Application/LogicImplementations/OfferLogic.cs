using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Shared.DTOs;
using Offer = Shared.Models.Offer;

namespace Application.LogicImplementations;

/// <summary>
/// Offer logic that implements from <see cref="IOfferDao"/> that is responsible for checking and validating data
/// </summary>
public class OfferLogic : IOfferLogic
{
    private IOfferDao offerDao;

    /// <summary>
    /// Initializing the OfferLogic with the given IOfferDao
    /// </summary>
    /// <param name="offerDao"></param>
    public OfferLogic(IOfferDao offerDao)
    {
        this.offerDao = offerDao;
    }
    
    /// <summary>
    /// Create asynchronously a Offer using the OfferCreationDto object. Call the CreateAsync method in gRPC client 
    /// </summary>
    /// <param name="dto">The object holding all the offer information</param>
    /// <returns>The created Offer object</returns>
    public async Task<Offer> CreateAsync(OfferCreationDto dto)
    {

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
            ImagePath = dto.ImagePath
        };

        await offerDao.CreateAsync(offerToSend);
        
        return offerToSend;
    }

    /// <summary>
    /// Getting asynchronously all the offers through the IOfferDao
    /// </summary>
    /// <returns>A Collection of Offers</returns>
    public async Task<IEnumerable<Offer>> GetAsync()
    {
        return await offerDao.GetAsync();
    }

    
}