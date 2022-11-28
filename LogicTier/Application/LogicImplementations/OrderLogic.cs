using System.Collections;
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
        IEnumerable<OrderOffer> orderOffersList = await orderDao.GetOrdersOffersAsync(username);

        //TODO change to farmName
        //sorting OrderItems into Order
        var orders = from orderOffer in orderOffersList
            group orderOffer by orderOffer.Offer.Name
            into order
            select new { FarmName = order.Key, OrderOffer = order.ToList() };

        List<Order> ordersToSend = new List<Order>();
        foreach (var order in orders)
        {
            List<OrderOffer> deliveryOffers = new List<OrderOffer>();
            List<OrderOffer> pickUpOffers = new List<OrderOffer>();
            foreach (var orderOffer in order.OrderOffer)
            {
                if (orderOffer.CollectionOption.Equals("delivery"))
                {
                    deliveryOffers.Add(orderOffer);
                }
                else if (orderOffer.CollectionOption.Equals("pickUp"))
                {
                    pickUpOffers.Add(orderOffer);
                }
            }

            if (deliveryOffers.Any())
            {
                Order orderToSend = new Order
                {
                    OrderOffers = deliveryOffers,
                    IsDone = false,
                    FarmName = order.FarmName,
                    CollectionOption = "delivery"
                };
                ordersToSend.Add(orderToSend);
            }

            if (pickUpOffers.Any())
            {
                Order orderToSend = new Order
                {
                    OrderOffers = deliveryOffers,
                    IsDone = false,
                    FarmName = order.FarmName,
                    CollectionOption = "pickUp"
                };
                ordersToSend.Add(orderToSend);
            }
        }

        await orderDao.CreateOrdersAsync(ordersToSend);
    }

    public Task<IEnumerable<Order>> GetAllOrders(string username)
    {
        return orderDao.GetAllOrdersAsync(username);
    }
}