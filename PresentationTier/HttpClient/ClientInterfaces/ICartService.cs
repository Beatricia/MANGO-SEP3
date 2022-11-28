using Shared.Models;

namespace HttpClient.ClientInterfaces;

public interface ICartService
{
    public Task<User> AddToCartAsync(CartOfferDto dto);
    public Task<ICollection<CartOffer>> GetAllCartItemsAsync(string username);
    public Task DeleteAllCartOffersAsync(string username);

}