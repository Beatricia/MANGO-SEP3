using System.Runtime.CompilerServices;
using Application.DAOInterfaces;
using Google.Protobuf.Collections;
using GprcClients.Converters;
using Grpc.Core;
using Shared.Models;
using Offer = Shared.Models.Offer;
using Order = Shared.Models.Order;

namespace GprcClients.DAOImplementations;

public class OrderDaoImpl : IOrderDao
{
    private OrderService.OrderServiceClient orderService;
    private IImageDao imageDao;

    /// <summary>
    /// Initializes the OrderDaoImpl with the given gRPC client service
    /// </summary>
    /// <param name="offerService"></param>
    public OrderDaoImpl(OrderService.OrderServiceClient orderService, IImageDao imageDao)
    {
        this.orderService = orderService;
        this.imageDao = imageDao;
    }
    

    public async Task<IEnumerable<Shared.Models.Order>> GetAllOrdersAsync(string username)
    {
        var text = new Text { Text_ = username };

        try
        {
            Orders orders = await orderService.GetAllOrdersAsync(text);
            ICollection<Shared.Models.Order> ordersToReturn = new List<Shared.Models.Order>();
            foreach (var order in orders.Orders_)
            {
                ordersToReturn.Add(order.ToShared(imageDao));
            }

            return ordersToReturn;
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }


    public async Task CreateOrdersAsync(IEnumerable<Shared.Models.Order> orders)
    {
        List<global::Order> ordersGrpc = new List<global::Order>();
        foreach (var order in orders)
        {
            Console.WriteLine("Order: " + order.CollectionOption);
            ordersGrpc.Add(order.ToGrpc());
        }

        var ordersToCreate = new Orders();
        ordersToCreate.Orders_.AddRange(ordersGrpc);
        try
        {
            await orderService.CreateOrdersAsync(ordersToCreate);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }

    public async Task<IEnumerable<Shared.Models.OrderOffer>> GetOrdersOffersAsync(string username)
    {
        var text = new Text { Text_ = username };

        try
        {
            OrderOffers orderOffers = await orderService.GetOrderOffersAsync(text);
            return ConvertOrderOffersFromGrpc(orderOffers);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }

    public async Task CompleteOrderAsync(int id)
    {
        Id orderId = new Id
        {
            Id_ = id
        };
        try
        {
            await orderService.CompleteOrderAsync(orderId);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }

    public async Task DeleteOrderAsync(int id)
    {
        var text = new Text { Text_ = String.Format("{0}", id) };

        try
        {
            await orderService.DeleteOrderAsync(text);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }

    public async Task<Shared.Models.Order> GetOrderAsync(int orderId)
    {
        var id = new Id() { Id_ = orderId};

        try
        {
            global::Order order = await orderService.GetOrderByIdAsync(id);
            return order.ToShared(imageDao);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }

    private IEnumerable<Shared.Models.OrderOffer> ConvertOrderOffersFromGrpc(OrderOffers orderOffers)
    {
        IEnumerable<OrderOffer> orderOffersList = orderOffers.OrderOffers_;
        List<Shared.Models.OrderOffer> listToReturn = new List<Shared.Models.OrderOffer>();
        foreach (var orderOffer in orderOffersList)
        {
            var item = orderOffer.ToShared(imageDao);
            
            listToReturn.Add(item);
        }

        return listToReturn;
    }
}