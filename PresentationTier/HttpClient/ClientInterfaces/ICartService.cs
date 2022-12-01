using Shared.Models;

namespace HttpClient.ClientInterfaces;

public interface ICartService
{
    public Task AddToCartAsync(CartOfferDto dto);
    public Task<ICollection<CartOffer>> GetAllCartItemsAsync();
    public Task DeleteAllCartOffersAsync();

    public Task DeleteCartOfferAsync(int cartItemId);
}