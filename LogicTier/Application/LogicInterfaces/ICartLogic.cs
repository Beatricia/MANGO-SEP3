using Shared.Models;

namespace Application.LogicInterfaces;

public interface ICartLogic
{
    public Task AddToCartAsync(CartOfferDto dto);
    public Task<IEnumerable<CartOffer>> GetAllCartItemsAsync(string username);
    public Task DeleteAllCartOffersAsync(string username);
}