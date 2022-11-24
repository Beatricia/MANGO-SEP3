using Application.DAOInterfaces;
using Shared.Models;
using Offer = Shared.Models.Offer;

namespace GprcClients.DAOImplementations;

public class OrderDaoImpl : IOrderDao
{
    private OrderService.OrderServiceClient client;

    /// <summary>
    /// Initializes the OrderDaoImpl with the given gRPC client service
    /// </summary>
    /// <param name="offerService"></param>
    public OrderDaoImpl(OrderService.OrderServiceClient client)
    {
        this.client = client;
    }

    public Task CreateOrderOffersAsync(List<OrderOffer> order)
    {
        throw new NotImplementedException();
    }

    public Task CreateOrderOffersAsync(List<Shared.Models.OrderOffer> order)
    {
        throw new NotImplementedException();
    }

    Task<IEnumerable<Shared.Models.Order>> IOrderDao.GetAllOrdersAsync(string Username)
    {
        throw new NotImplementedException();
    }

    public Task CreateOrdersAsync(IEnumerable<Shared.Models.Order> orders)
    {
        throw new NotImplementedException();
    }

    Task<IEnumerable<Shared.Models.OrderOffer>> IOrderDao.GetOrdersOffersAsync(string username)
    {
        throw new NotImplementedException();
    }

    public Task<IEnumerable<Order>> GetAllOrdersAsync(string Username)
    {
        throw new NotImplementedException();
    }

    public Task CreateOrdersAsync(IEnumerable<Order> orders)
    {
        throw new NotImplementedException();
    }

    public async Task<IEnumerable<Shared.Models.OrderOffer>> GetOrdersOffersAsync(string username)
    {
        var text = new Text { Text_ = username };

        try
        {
            OrderOffers orderOffers = await client.GetOrderOffersAsync(text);
            return ConvertFromGrpc(orderOffers);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }

    private IEnumerable<Shared.Models.OrderOffer> ConvertFromGrpc(OrderOffers orderOffers)
    {
        IEnumerable<OrderOffer> orderOffersList = orderOffers.OrderOffers_;
        List<Shared.Models.OrderOffer> listToReturn = new List<Shared.Models.OrderOffer>();
        foreach (var orderOffer in orderOffersList)
        {
            Shared.Models.OrderOffer item = new Shared.Models.OrderOffer
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
}