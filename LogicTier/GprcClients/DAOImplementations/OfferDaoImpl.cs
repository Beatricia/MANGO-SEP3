using Application.DAOInterfaces;

namespace GprcClients.DAOImplementations;

/// <summary>
/// The Implementation of <see cref="IOfferDao"/> that takes the data from the Data Tier through OfferServiceClient
/// </summary>
public class OfferDaoImpl : IOfferDao 
{
    private OfferService.OfferServiceClient offerService;
    private IImageDao _imageDao;

    /// <summary>
    /// Initializes the OfferDaoImpl with the given gRPC client service
    /// </summary>
    /// <param name="offerService"></param>
    public OfferDaoImpl(OfferService.OfferServiceClient offerService, IImageDao imageDao)
    {
        this.offerService = offerService;
        _imageDao = imageDao;
    }
    
    
    public async Task<Shared.Models.Offer> CreateAsync(Shared.Models.Offer offer)
    {
        Offer response = ConvertOfferToGrpc(offer);//;await offerService.CreateOfferAsync(offerToCreate);
         
        var offerToCreate = ConvertOfferToGrpc(offer);
        
        var returnedOffer = await offerService.CreateOfferAsync(offerToCreate);

        return ConvertOfferToShared(returnedOffer);
    }

    private Offer ConvertOfferToGrpc(Shared.Models.Offer offer)
    {
        // Convert shared offer to grpc
        var offerToCreate = new Offer
        {
            Id = offer.Id,
            Name = offer.Name,
            Quantity = offer.Quantity,
            Unit = offer.Unit,
            Price = offer.Price,
            Delivery = offer.Delivery,
            PickUp = offer.PickUp,
            PickYourOwn = offer.PickYourOwn,
            Description = offer.Description,
            FarmName = offer.FarmName,
        };

        return offerToCreate;
    }

    /// <summary>
    /// Getting all the offers from the database 
    /// </summary>
    /// <returns> A Collection of Offers </returns>
    public async Task<IEnumerable<Shared.Models.Offer>> GetAsync()
    {
        // Getting the offers from the database as an OfferItem class from the buffer
        OfferItems offersBuff = await offerService.GetOffersAsync(new Void());

        // Creating a new list with the Offers from the LogicTier models
        var list = new List<Shared.Models.Offer>();
        // For each offerBuff from the database 
        foreach (var created in offersBuff.Offers)
        {
            if (created is null)
                continue;
            // Creating a new instance of Model offer
            Shared.Models.Offer offerToPresentationTier = ConvertOfferToShared(created);
            // Adding the new created offer to the list
            list.Add(offerToPresentationTier);
        }

        // Returning the list with all the offers
        return list;
    }
    
    
    public async Task<Shared.Models.Offer> GetOfferByIdAsync(int id)
    {
        Id offerId = new Id
        {
            Id_ = id
        };

        Offer offer = await offerService.GetOfferByIdAsync(offerId);

        Shared.Models.Offer offerToSend = new Shared.Models.Offer
        {
            Id = offer.Id,
            Name = offer.Name,
            Quantity = offer.Quantity,
            Unit = offer.Unit,
            Price = offer.Price,
            Delivery = offer.Delivery,
            PickUp = offer.PickUp,
            PickYourOwn = offer.PickYourOwn,
            Description = offer.Description,
        };
        return offerToSend;
    }
    
    
    // convert from grpc object to shared offer
    private Shared.Models.Offer ConvertOfferToShared(Offer offer)
    {
        return new Shared.Models.Offer
        {
            Id = offer.Id,
            Name = offer.Name,
            Quantity = offer.Quantity,
            Unit = offer.Unit,
            Price = offer.Price,
            Delivery = offer.Delivery,
            PickUp = offer.PickUp,
            PickYourOwn = offer.PickYourOwn,
            Description = offer.Description,
            Image = _imageDao.GetImageForOffer(offer.Id),
            FarmName = offer.FarmName
        };
    }
}