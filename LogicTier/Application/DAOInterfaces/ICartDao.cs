using Shared.DTOs;
using Shared.Models;

namespace Application.DAOInterfaces;

public interface ICartDao
{
    public Task AddToCartAsync(CartOffer cartOffer);
    public Task<ICollection<CartOffer>> GetAllCartItemsAsync(string username);
    public Task DeleteAllCartOffersAsync(string username);
    public Task<CartOffer?> GetByIdAsync(int id);
    public Task DeleteCartOfferAsync (int id);
    public Task DeleteAllByOfferIdAsync(int offerId);
    Task UpdateAsync(UpdateCartOfferDto dto);
}