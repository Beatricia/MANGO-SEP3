using System.Collections;
using System.Text.Json;
using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Shared.Models;

namespace Application.LogicImplementations;

public class OrderLogic : IOrderLogic
{
    private readonly IOrderDao orderDao;
    private readonly ICartDao cartDao;
    private readonly IOfferDao offerDao;

    public OrderLogic(IOrderDao orderDao,ICartDao cartDao,IOfferDao offerDao)
    {
        this.orderDao = orderDao;
        this.cartDao = cartDao;
        this.offerDao = offerDao;
    }

    public async Task CreateOrderAsync(string username)
    {
        ICollection<CartOffer> cartOffers = await cartDao.GetAllCartItemsAsync(username);
        List<OrderOffer> orderOffers = new List<OrderOffer>();
        
        //converting cartItems into OrderItems
        foreach (var cartOffer in cartOffers)
        {
            Offer offer = await offerDao.GetOfferByIdAsync(cartOffer.Id);
            OrderOffer orderOffer = new OrderOffer
            {
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

        Console.WriteLine(JsonSerializer.Serialize(orderOffersList));

        //sorting OrderItems into Order
        var orders = from orderOffer in orderOffersList
            group orderOffer by orderOffer.Offer.FarmName
            into order
            select new { FarmName = order.Key, OrderOffer = order.ToList() };

        List<Order> ordersToSend = new List<Order>();
        foreach (var order in orders)
        {
            List<OrderOffer> deliveryOffers =
                order.OrderOffer.Where(offer => offer.CollectionOption == "delivery").ToList();
            List<OrderOffer> pickUpOffers = 
                order.OrderOffer.Where(offer => offer.CollectionOption == "pickUp").ToList();
            
            
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
                    OrderOffers = pickUpOffers,
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