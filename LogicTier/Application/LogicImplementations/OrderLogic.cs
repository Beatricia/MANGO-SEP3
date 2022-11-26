using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Shared.Models;

namespace Application.LogicImplementations;

public class OrderLogic : IOrderLogic
{
    private readonly IOrderDao orderDao;
    private readonly ICartDao cartDao;
    private readonly IOfferDao OfferDao;

    public OrderLogic(IOrderDao orderDao,ICartDao cartDao,IOfferDao offerDao)
    {
        this.orderDao = orderDao;
        this.cartDao = cartDao;
        this.OfferDao = offerDao;
    }

    public async Task CreateOrderAsync(string username)
    {
        ICollection<CartOffer> cartOffers =  cartDao.GetAllCartItemsAsync(username).GetAwaiter().GetResult();
        List<OrderOffer> orderOffers = new List<OrderOffer>();
        
        //converting cartItems into OrderItems
        foreach (var cartOffer in cartOffers)
        {
            Offer offer = await OfferDao.GetOfferByIdAsync(cartOffer.Id);
            OrderOffer orderOffer = new OrderOffer
            {
                Id = cartOffer.Id,
                Offer = offer,
                Quantity = cartOffer.Quantity,
                Username = cartOffer.UserName,
                CollectionOption = cartOffer.CollectionOption,
            };
            orderOffers.Add(orderOffer);
        }

        //adding OrderItems to database
        await orderDao.CreateOrderOffersAsync(orderOffers);
        
        //sorting OrderItems into Order
        
        
        
        
    }

    public Task<IEnumerable<Order>> GetAllOrders(string username)
    {
        return orderDao.GetAllOrdersAsync(username);
    }
}