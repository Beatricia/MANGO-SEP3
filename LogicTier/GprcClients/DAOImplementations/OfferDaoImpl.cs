using Application.DAOInterfaces;

namespace GprcClients.DAOImplementations;

public class OfferDaoImpl : IOfferDao
{
    private OfferService.OfferServiceClient offerService;

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

    public async Task<IEnumerable<Shared.Models.Offer>> GetAsync()
    {
        OfferItems offersBuff = await offerService.GetOffersAsync(new Void());

        var list = new List<Shared.Models.Offer>();
        foreach (var created in offersBuff.Offers)
        {
            if (created is null)
                continue;

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
            list.Add(offerToPresentationTier);
        }

        return list;
    }
}