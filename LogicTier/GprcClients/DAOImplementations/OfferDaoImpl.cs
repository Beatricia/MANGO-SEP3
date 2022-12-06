using Application.DAOInterfaces;
using GprcClients.Converters;
using Shared.DTOs;

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
        var offerToCreate = offer.ToGrpc();
        var returnedOffer = await offerService.CreateOfferAsync(offerToCreate);

        return returnedOffer.ToShared(_imageDao);
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
            Shared.Models.Offer offerToPresentationTier = created.ToShared(_imageDao);
            // Adding the new created offer to the list
            list.Add(offerToPresentationTier);
        }

        // Returning the list with all the offers
        return list;
    }
    
    
    public async Task<Shared.Models.Offer> GetOfferByIdAsync(int id)
    {
        Id offerId = id.ToGrpc();

        Offer offer = await offerService.GetOfferByIdAsync(offerId);

        return offer.ToShared(_imageDao);
    }

    public async Task<IEnumerable<Shared.Models.Offer>> GetByFarmNameAsync(string farmName)
    {
        var farmNameGrpc = farmName.ToGrpc();
        OfferItems offersBuff = await offerService.GetOffersByFarmNameAsync(farmNameGrpc );
        
        var list = new List<Shared.Models.Offer>();
        foreach (var created in offersBuff.Offers)
        {
            if (created is null)
                continue;
            Shared.Models.Offer offerToPresentationTier = created.ToShared(_imageDao);
            list.Add(offerToPresentationTier);
        }
        return list;
    }

    /// <summary>
    /// Will disable the offer with the given id
    /// </summary>
    public async Task DisableAsync(int id)
    {
        Id offerId = id.ToGrpc();
        
        await offerService.DisableOfferByIdAsync(offerId);
    }
}