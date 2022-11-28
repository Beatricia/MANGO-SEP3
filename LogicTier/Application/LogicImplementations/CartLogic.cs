using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Shared.Models;

namespace Application.LogicImplementations;

public class CartLogic : ICartLogic
{
    private ICartDao cartDao;
    private IAuthDao authDao;
    private IOfferDao offerDao;

    public CartLogic(ICartDao cartDao, IAuthDao authDao, IOfferDao offerDao)
    {
        this.cartDao = cartDao;
        this.authDao = authDao;
        this.offerDao = offerDao;
    } 
        
    public async Task AddToCartAsync(CartOfferDto dto)
    {
        
        if (dto.Quantity <= 0)
        {
            throw new Exception("The quantity should be bigger than 0");
        }

        if (dto.Quantity >= 100)
        {
            throw new Exception("The quantity has to be smaller than 250");
        }

        /*  if (!dto.CollectOption.ToLower().Equals("Delivery".ToLower()) ||
              !dto.CollectOption.ToLower().Equals("Pick Up".ToLower()) ||
              !dto.CollectOption.ToLower().Equals("Pick Your Own".ToLower()))
          {
              throw new Exception("Invalid collection option for the cart offer");
          }
          */

        await cartDao.AddToCartAsync(dto);
    }
    

    public async Task<IEnumerable<CartOffer>> GetAllCartItemsAsync(string username)
    {
        if (authDao.GetUserAsync(username).Equals(null))
        {
            throw new Exception("Invalid Username. This user does not exist");
        }

        IEnumerable<CartOffer> cartOffers = await cartDao.GetAllCartItemsAsync(username);
        return cartOffers;
    }

    public async Task DeleteAllCartOffersAsync(string username)
    {
        if (authDao.GetUserAsync(username).Equals(null))
        {
            throw new Exception("Invalid Username. This user does not exist");
        }

        await cartDao.DeleteAllCartOffersAsync(username);
    }
}