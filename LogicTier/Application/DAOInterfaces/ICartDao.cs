using Shared.Models;

namespace Application.DAOInterfaces;

public interface ICartDao
{
    public Task<User> AddToCartAsync(CartOfferDto dto);
    public Task<ICollection<CartOffer>> GetAllCartItemsAsync(string username);
    public Task DeleteAllCartOffersAsync(string username);
}