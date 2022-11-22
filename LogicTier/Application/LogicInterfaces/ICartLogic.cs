using Shared.Models;

namespace Application.LogicInterfaces;

public interface ICartLogic
{
    public Task<User> AddToCartAsync(CartOfferDto dto);
    public Task<IEnumerable<CartOffer>> GetAllCartItemsAsync(string username);
}