using Application.DAOInterfaces;
using Shared.Models;

namespace GprcClients.DAOImplementations;

public class CartDaoImpl : ICartDao
{
    private CartOfferService.CartOfferServiceClient cartOfferServiceClient;
    private OfferService.OfferServiceClient offerServiceClient;

    public CartDaoImpl(CartOfferService.CartOfferServiceClient cartOfferServiceClient, OfferService.OfferServiceClient offerServiceClient )
    {
        this.cartOfferServiceClient = cartOfferServiceClient;
        this.offerServiceClient = offerServiceClient;
    }
    
    public async Task AddToCartAsync(CartOfferDto dto)
    {
        // wew need to convert the dto to a proto bc when calling it 
        // from the cartOfferServiceClient we need to insert a proto
        var toAddCartOffer = new global::CartOffer
        {
            OfferId = dto.OfferId,
            Quantity = dto.Quantity,
            Username = dto.Username,
            CollectionOption = dto.CollectOption
        };

        _ = await cartOfferServiceClient.AddToCartAsync(toAddCartOffer);
    }

    public async Task<ICollection<Shared.Models.CartOffer>> GetAllCartItemsAsync(string username)
    {
        // converting the proto user
        var user = new Username
        {
            Username_ = username
        };

        // Getting the cartOffers from the database as CartOffers class from the buffer
        // the param from the GetAll ... is a proto 
        CartOffers cartOffers = await cartOfferServiceClient.GetAllCartOffersAsync(user);
        // Getting the offers from the database as an OfferItem class from the buffer
        OfferItems offersBuff = await offerServiceClient.GetOffersAsync(new Void());

        ICollection<Shared.Models.CartOffer> list = new List<Shared.Models.CartOffer>();

        foreach (var cartOffer in cartOffers.CartOffers_)
        {
            if (cartOffer is null)
                continue;
            
            Shared.Models.Offer off = new Shared.Models.Offer();
            foreach (var offer in offersBuff.Offers)
            {
                if (offer.Id == cartOffer.OfferId)
                {
                    off.Id = offer.Id;
                    off.Delivery = offer.Delivery;
                    off.Name = offer.Name;
                    off.Price = offer.Price;
                    off.FarmName = offer.FarmName;
                    off.Description = offer.Description;
                    off.Quantity = offer.Quantity;
                    off.Unit = offer.Unit;
                    off.PickYourOwn = offer.PickYourOwn;
                    off.PickUp = offer.PickUp;
                }
            }
            
            //Creating a new instance of CartOffer from the Model
            Shared.Models.CartOffer cartOfferToSend = new Shared.Models.CartOffer()
            {
                Id = cartOffer.Id,
                Offer = off,
                Quantity = cartOffer.Quantity,
                UserName = cartOffer.Username,
                CollectionOption = cartOffer.CollectionOption
            };
            list.Add(cartOfferToSend);
        }

        return list;
    }
    
    public async Task DeleteAllCartOffersAsync(string username)
    {
        var user = new Username
        {
            Username_ = username
        };

       await cartOfferServiceClient.DeleteAllCartOffersAsync(user);
    }

    public async Task<Shared.Models.CartOffer?> GetByIdAsync(int id)
    {
        var idBuff = new Id { Id_ = id };
        global::CartOffer? existing = await cartOfferServiceClient.GetByIdAsync(idBuff);
        // Getting the offers from the database as an OfferItem class from the buffer
        OfferItems offersBuff = await offerServiceClient.GetOffersAsync(new Void());
        Shared.Models.Offer off = new Shared.Models.Offer();
        
        //getting the offer by id
        foreach (var offer in offersBuff.Offers)
        {
            if (offer.Id == existing.OfferId)
            {
                off.Id = offer.Id;
                off.Delivery = offer.Delivery;
                off.Name = offer.Name;
                off.Price = offer.Price;
                off.FarmName = offer.FarmName;
                off.Description = offer.Description;
                off.Quantity = offer.Quantity;
                off.Unit = offer.Unit;
                off.PickYourOwn = offer.PickYourOwn;
                off.PickUp = offer.PickUp;
            }
        }
        //converting to model
        Shared.Models.CartOffer coModel = new Shared.Models.CartOffer()
        {
            Id = existing.Id,
            Offer = off,
            Quantity = existing.Quantity,
            UserName = existing.Username,
            CollectionOption = existing.CollectionOption
        };

        return await Task.FromResult(coModel);
    }

    public async Task DeleteCartOfferAsync(int id)
    {
        var idBuff = new Id { Id_ = id };
        global::CartOffer? existing = await cartOfferServiceClient.GetByIdAsync(idBuff);
        
        if (existing == null)
        {
            throw new Exception($"The cart item with id {id} could not be found");
        }
        
        cartOfferServiceClient.DeleteCartOfferAsync(idBuff);
    }
}