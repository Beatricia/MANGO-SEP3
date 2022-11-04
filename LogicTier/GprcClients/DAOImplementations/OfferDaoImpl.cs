using Application.DAOInterfaces;

namespace GprcClients.DAOImplementations;

/// <summary>
/// The Implementation of <see cref="IOfferDao"/> that takes the data from the Data Tier through OfferServiceClient
/// </summary>
public class OfferDaoImpl : IOfferDao 
{
    private OfferService.OfferServiceClient offerService;

    /// <summary>
    /// Initializes the OfferDaoImpl with the given gRPC client service
    /// </summary>
    /// <param name="offerService"></param>
    public OfferDaoImpl(OfferService.OfferServiceClient offerService)
    {
        this.offerService = offerService;
    }
    
    
    public async Task CreateAsync(Shared.Models.Offer offer)
    {
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
            ImagePath = offer.ImagePath
        };
        
        _ = await offerService.CreateOfferAsync(offerToCreate);
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
            Shared.Models.Offer offerToPresentationTier = new Shared.Models.Offer
            {
                Id = created.Id,
                Name = created.Name,
                Quantity = created.Quantity,
                Unit = created.Unit,
                Price = created.Price,
                Delivery = created.Delivery,
                PickUp = created.PickUp,
                PickYourOwn = created.PickYourOwn,
                Description = created.Description,
                ImagePath = created.ImagePath
            };
            // Adding the new created offer to the list
            list.Add(offerToPresentationTier);
        }

        // Returning the list with all the offers
        return list;
    }
}