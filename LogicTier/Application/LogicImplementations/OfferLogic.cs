using System.Collections;
using System.Collections.ObjectModel;
using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Google.Protobuf.Collections;
using Google.Protobuf.WellKnownTypes;
using Microsoft.VisualBasic;
using Offer = Shared.Models.Offer;

namespace Application.LogicImplementations;

//TODO ask Uafa how to create the OfferService
public class OfferLogic : IOfferLogic
{
    private OfferService.OfferServiceClient offerServiceClient;

    public OfferLogic(OfferService.OfferServiceClient offerServiceClient)
    {
        this.offerServiceClient = offerServiceClient;
    }
    
    public Task CreateAsync()
    {
        throw new NotImplementedException();
    }

    public async Task<IEnumerable<Shared.Models.Offer>> GetAsync()
    {
        IEnumerable<Shared.Models.Offer> offers = new List<Shared.Models.Offer>();
        
        //OfferItems is a protobuff thingy 
       global::
           OfferItems offersBuff = await offerServiceClient.GetOffersAsync(new Void());

       for (int i = 0; i < offersBuff.CalculateSize(); i++)
       {
           global::Offer newBuffOfferItem = offersBuff.Offers[i];
           Shared.Models.Offer offerToPresentationTier = new Shared.Models.Offer
           {
               Title = newBuffOfferItem.Title,
               OfferId = newBuffOfferItem.OfferId,
               Price = newBuffOfferItem.Price,
               Description = newBuffOfferItem.Description,
               PhotoPath = newBuffOfferItem.PhotoPath
           };
           offers.Append(offerToPresentationTier);
       }
       return offers;
    }
}