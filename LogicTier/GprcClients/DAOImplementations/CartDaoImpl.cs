using Application.DAOInterfaces;
using GprcClients.Converters;
using Shared.DTOs;

namespace GprcClients.DAOImplementations;

public class CartDaoImpl : ICartDao
{
    private CartOfferService.CartOfferServiceClient cartOfferServiceClient;
    private IImageDao imageDao;

    public CartDaoImpl(CartOfferService.CartOfferServiceClient cartOfferServiceClient, IImageDao imageDao )
    {
        this.cartOfferServiceClient = cartOfferServiceClient;
        this.imageDao = imageDao;

    }
    
    public async Task AddToCartAsync(Shared.Models.CartOffer cartOffer)
    {
        // wew need to convert the dto to a proto bc when calling it 
        // from the cartOfferServiceClient we need to insert a proto
        var toAddCartOffer = cartOffer.ToGrpc();
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

        ICollection<Shared.Models.CartOffer> list = new List<Shared.Models.CartOffer>();

        foreach (var cartOffer in cartOffers.CartOffers_)
        { 
            if (cartOffer is null)
                continue;
            
            
            Shared.Models.CartOffer cartOfferToSend = cartOffer.ToShared(imageDao);
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
        var idBuff = id.ToGrpc();
        CartOffer? existing = await cartOfferServiceClient.GetByIdAsync(idBuff);

        if(existing is null)
            return null;
        
        return existing.ToShared(imageDao);
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

    public async Task DeleteAllByOfferIdAsync(int offerId)
    {
        var idBuff = offerId.ToGrpc();
        
        await cartOfferServiceClient.DeleteAllByOfferIdAsync(idBuff);
    }

    public async Task UpdateAsync(UpdateCartOfferDto dto)
    {
        var cartOfferToUpdate = dto.ToGrpc();
        await cartOfferServiceClient.UpdateCartOfferAsync(cartOfferToUpdate);
    }
}