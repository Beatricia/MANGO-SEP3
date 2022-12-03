using System.Collections;
using System.Text.Json;
using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Shared.DTOs;
using Shared.Models;

namespace Application.LogicImplementations;

public class OrderLogic : IOrderLogic
{
    private readonly IOrderDao orderDao;
    private readonly ICartDao cartDao;
    private readonly IOfferDao offerDao;
    private readonly IUserDao userDao;
    private readonly INotificationLogic notificationLogic;
    private readonly IFarmDao farmDao;

    public OrderLogic(IOrderDao orderDao, ICartDao cartDao, IOfferDao offerDao, IUserDao userDao, INotificationLogic notificationLogic,IFarmDao farmDao)
    {
        this.orderDao = orderDao;
        this.cartDao = cartDao;
        this.offerDao = offerDao;
        this.userDao = userDao;
        this.notificationLogic = notificationLogic;
        this.farmDao = farmDao;
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
            //offer => offer.CollectionOption == "delivery"
            List<CartOffer> deliveryOffers =
                order.CartOffer.Where(offer => offer.CollectionOption == CollectionOption.Delivery).ToList();
            
            //offer => offer.CollectionOption == "pickUp"
            List<CartOffer> pickUpOffers =
                order.CartOffer.Where(offer => offer.CollectionOption == CollectionOption.PickUp).ToList();

            if (deliveryOffers.Any())
            {
                Order orderToSend = new Order
                {
                    //---> we don't really need to assign CartOffers since we use the username to get them in the db
                    //CartOffers = deliveryOffers,
                    IsDone = false,
                    FarmName = order.FarmName,
                    //CollectionOption = "delivery",
                    CollectionOption = CollectionOption.Delivery,
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
                    //CollectionOption = "pickUp",
                    CollectionOption = CollectionOption.PickUp,
                    Username = username
                };
                ordersToSend.Add(orderToSend);
            }
        }
        await orderDao.CreateOrdersAsync(ordersToSend);
    }

    public async Task<IEnumerable<Order>> GetAllOrders(string username)
    {
        var orders = await orderDao.GetAllOrdersAsync(username);
        return  orders;
    }

    public async Task CompleteOrderAsync(int id)
    {
        await orderDao.CompleteOrderAsync(id);
    }

    public async Task DeleteOrderAsync(int orderId, string username)
    {
        try
        {
            Order order = await orderDao.GetOrderAsync(orderId);
            
            Customer? customer = await userDao.GetCustomer(username);
            Farmer? farmer = await userDao.GetFarmer(username);

            var notification = new NotificationCreationDto();
            
            if (customer != null)
            {
                Farm farm = await farmDao.GetFarmByNameAsync(order.FarmName);
                notification.Username = farm.Farmer.Username;
                notification.Text = $"User: {username} canceled order";
            }
            
            else if(farmer != null)
            {
                notification.Username = order.Username;
                notification.Text = $"Farm: {order.FarmName} canceled your order";
            }
            
            await orderDao.DeleteOrderAsync(orderId);
            await notificationLogic.AddNotificationAsync(notification);
        }
        catch (Exception e)
        {
            Console.WriteLine("Order does no longer exist");
            throw;
        }
        
    }
}