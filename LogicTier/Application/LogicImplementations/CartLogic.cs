using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Shared.Models;

namespace Application.LogicImplementations;

public class CartLogic : ICartLogic
{
    private ICartDao cartDao;

    public CartLogic(ICartDao cartDao)
    {
        this.cartDao = cartDao;
    } 
        
    public async Task<User> AddToCartAsync(CartOfferDto dto)
    {
        if (dto.Quantity <= 0)
        {
            throw new Exception("The quantity should be bigger than 0");
        }

        if (dto.Quantity >= 250)
        {
            throw new Exception("The quantity has to be smaller than 250");
        }
        
        return await cartDao.AddToCartAsync(dto);
    }

    public Task<IEnumerable<CartOffer>> GetAllCartItemsAsync(string username)
    {
        throw new NotImplementedException();
    }
}