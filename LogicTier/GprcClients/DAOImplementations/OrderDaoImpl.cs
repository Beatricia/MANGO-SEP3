using System.Runtime.CompilerServices;
using Application.DAOInterfaces;
using Google.Protobuf.Collections;
using Grpc.Core;
using Shared.Models;
using Offer = Shared.Models.Offer;
using Order = Shared.Models.Order;

namespace GprcClients.DAOImplementations;

public class OrderDaoImpl : IOrderDao
{
    private OfferService.OfferServiceClient offerService;
    private OrderService.OrderServiceClient orderService;

    /// <summary>
    /// Initializes the OrderDaoImpl with the given gRPC client service
    /// </summary>
    /// <param name="offerService"></param>
    public OrderDaoImpl(OfferService.OfferServiceClient offerService, OrderService.OrderServiceClient orderService)
    {
        this.offerService = offerService;
        this.orderService = orderService;
    }

    public async Task CreateOrderOffersAsync(List<Shared.Models.OrderOffer> orderOffers)
    {
        OrderOffers orderOffersGrpc = ConvertOrderOffersToGrpc(orderOffers);

        try
        {
            await orderService.CreateOrderOffersAsync(orderOffersGrpc);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }
    

    public async Task<IEnumerable<Shared.Models.Order>> GetAllOrdersAsync(string username)
    {
        var text = new Text { Text_ = username };

        try
        {
            Orders orders = await orderService.GetAllOrdersAsync(text);
            return ConvertOrdersFromGrpc(orders);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }


    public async Task CreateOrdersAsync(IEnumerable<Shared.Models.Order> orders)
    {
        Orders ordersGrpc = ConvertOrdersToGrpc(orders);
        try
        {
            await orderService.CreateOrdersAsync(ordersGrpc);
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

    private IEnumerable<Shared.Models.OrderOffer> ConvertOrderOffersFromGrpc(OrderOffers orderOffers)
    {
        IEnumerable<OrderOffer> orderOffersList = orderOffers.OrderOffers_;
        List<Shared.Models.OrderOffer> listToReturn = new List<Shared.Models.OrderOffer>();
        foreach (var orderOffer in orderOffersList)
        {
            var item = new Shared.Models.OrderOffer
            {
                Id = orderOffer.Id,
                CollectionOption = orderOffer.CollectionOption,
                Offer = ConvertOfferFromGrpc(orderOffer.Offer),
                Quantity = orderOffer.Quantity,
                Username = orderOffer.Username
            };
            listToReturn.Add(item);
        }

        return listToReturn;
    }

    private Shared.Models.Offer ConvertOfferFromGrpc(global::Offer offer)
    {
        Shared.Models.Offer offerToReturn = new Shared.Models.Offer
        {
            Name = offer.Name,
            Delivery = offer.Delivery,
            Description = offer.Description,
            Id = offer.Id,
            Price = offer.Price,
            Quantity = offer.Quantity,
            Unit = offer.Unit,
            ImagePath = offer.ImagePath,
            PickUp = offer.PickUp,
            PickYourOwn = offer.PickYourOwn
        };
        return offerToReturn;
    }

    private IEnumerable<Shared.Models.Order> ConvertOrdersFromGrpc(Orders orders)
    {
        IEnumerable<global::Order> ordersList = orders.Orders_;
        List<Shared.Models.Order> listToReturn = new List<Shared.Models.Order>();


        foreach (var order in ordersList)
        {
            OrderOffers orderOffers = new OrderOffers
            {
                OrderOffers_ = { order.OrderOffers }
            };
            List<Shared.Models.OrderOffer> orderOffersList = ConvertOrderOffersFromGrpc(orderOffers).ToList();

            var item = new Shared.Models.Order
            {
                Id = order.Id,
                CollectionOption = order.CollectionOption,
                FarmName = order.FarmName,
                IsDone = order.IsDone,
                OrderOffers = orderOffersList
            };
            listToReturn.Add(item);
        }

        return listToReturn;
    }
    
    private OrderOffers ConvertOrderOffersToGrpc(List<Shared.Models.OrderOffer> orderOffers)
    {
        List<OrderOffer> orderOffersGrpcList = new List<OrderOffer>();

        foreach (var orderOffer in orderOffers)
        {
            var item = new OrderOffer
            {
                Id = orderOffer.Id,
                Quantity = orderOffer.Quantity,
                Username = orderOffer.Username,
                CollectionOption = orderOffer.CollectionOption,
                Offer = ConvertOfferToGrpc(orderOffer.Offer)
            };
            orderOffersGrpcList.Add(item);
        }

        OrderOffers orderOffersGprc = new OrderOffers
        {
            OrderOffers_ = { orderOffersGrpcList }
        };
        return orderOffersGprc;
    }

    private global::Offer ConvertOfferToGrpc(Shared.Models.Offer offer)
    {
        var offerToReturn = new global::Offer
        {
            Name = offer.Name,
            Delivery = offer.Delivery,
            Description = offer.Description,
            Id = offer.Id,
            Price = offer.Price,
            Quantity = offer.Quantity,
            Unit = offer.Unit,
            ImagePath = offer.ImagePath,
            PickUp = offer.PickUp,
            PickYourOwn = offer.PickYourOwn
        };
        return offerToReturn;
    }
    
    private Orders ConvertOrdersToGrpc(IEnumerable<Shared.Models.Order> orders)
    {
        List<global::Order> ordersListGrpc = new List<global::Order>();

        foreach (var order in orders)
        {
           
            OrderOffers orderOffersGrpc = ConvertOrderOffersToGrpc(order.OrderOffers);
            RepeatedField<OrderOffer> orderOffersList = orderOffersGrpc.OrderOffers_;

            var orderGrpc = new global::Order
            {
                Id = order.Id,
                CollectionOption = order.CollectionOption,
                FarmName = order.FarmName,
                IsDone = order.IsDone,
                OrderOffers = { orderOffersList }
            };
            ordersListGrpc.Add(orderGrpc);
        }

        Orders ordersToReturn = new Orders
        {
            Orders_ = { ordersListGrpc }
        };
        return ordersToReturn;
    }
}