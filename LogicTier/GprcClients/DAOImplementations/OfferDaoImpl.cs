using Application.DAOInterfaces;
using Shared.Models;

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
    
    
    public async Task<Shared.Models.Offer> CreateAsync(Shared.Models.Offer offer)
    {
        var offerToCreate = ConvertOfferToGrpc(offer);
        
        var returnedOffer = await offerService.CreateOfferAsync(offerToCreate);

        return ConvertOfferToShared(returnedOffer);
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
    
    
    // convert from grpc object to shared offe
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
            Image = new Image()
            {
                RelativeUrl = offer.ImagePath
            },
            FarmName = offer.FarmName,
        };
    }
    
    // convert from shared offer to grpc object
    private Offer ConvertOfferToGrpc(Shared.Models.Offer offer)
    {
        // print out each property of the offer
        Console.WriteLine(offer.Id);
        Console.WriteLine(offer.Name);
        Console.WriteLine(offer.Quantity);
        Console.WriteLine(offer.Unit);
        Console.WriteLine(offer.Price);
        Console.WriteLine(offer.Delivery);
        Console.WriteLine(offer.PickUp);
        Console.WriteLine(offer.PickYourOwn);
        Console.WriteLine(offer.Description);
        Console.WriteLine(offer.Image.RelativeUrl);
        Console.WriteLine(offer.FarmName);
        
        
        return new Offer
        {
            Name = offer.Name,
            Quantity = offer.Quantity,
            Unit = offer.Unit,
            Price = offer.Price,
            Delivery = offer.Delivery,
            PickUp = offer.PickUp,
            PickYourOwn = offer.PickYourOwn,
            Description = offer.Description,
            ImagePath = offer.Image.RelativeUrl,
            FarmName = offer.FarmName,
        };
    }
}