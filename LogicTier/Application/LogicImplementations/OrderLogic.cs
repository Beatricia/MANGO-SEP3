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

    public OrderLogic(IOrderDao orderDao, ICartDao cartDao, IOfferDao offerDao)
    {
        this.orderDao = orderDao;
        this.cartDao = cartDao;
        this.offerDao = offerDao;
    }

    public async Task CreateOrderAsync(string username)
    {
        ICollection<CartOffer> cartOffers = await cartDao.GetAllCartItemsAsync(username);

        //sorting the cartItems to get types of orders
        var orders = from cartOffer in cartOffers
            group cartOffer by cartOffer.Offer.FarmName
            into order
            select new { FarmName = order.Key, CartOffer = order.ToList() };


        List<Order> ordersToSend = new List<Order>();
        foreach (var order in orders)
        {
            List<CartOffer> deliveryOffers =
                order.CartOffer.Where(offer => offer.CollectionOption == "delivery").ToList();
            List<CartOffer> pickUpOffers =
                order.CartOffer.Where(offer => offer.CollectionOption == "pickUp").ToList();

            if (deliveryOffers.Any())
            {
                Order orderToSend = new Order
                {
                    //---> we don't really need to assign CartOffers since we use the username to get them in the db
                    //CartOffers = deliveryOffers,
                    IsDone = false,
                    FarmName = order.FarmName,
                    CollectionOption = "delivery",
                    Username = username
                };
                ordersToSend.Add(orderToSend);
            }

            if (pickUpOffers.Any())
            {
                Order orderToSend = new Order
                {
                    //CartOffers = pickUpOffers,
                    IsDone = false,
                    FarmName = order.FarmName,
                    CollectionOption = "pickUp",
                    Username = username
                };
                ordersToSend.Add(orderToSend);
            }
        }
        await orderDao.CreateOrdersAsync(ordersToSend);
    }

    public async Task<IEnumerable<Order>> GetAllOrders(string username)
    {
        return  await orderDao.GetAllOrdersAsync(username);
    }

    public async Task CompleteOrderAsync(int id)
    {
        await orderDao.CompleteOrderAsync(id);
    }

    public Task DeleteOrderAsync(int OrderId)
    {
        try
        {
            return orderDao.DeleteOrderAsync(OrderId);
        }
        catch (Exception e)
        {
            Console.WriteLine("Order does no longer exist");
            throw;
        }
        
    }
}