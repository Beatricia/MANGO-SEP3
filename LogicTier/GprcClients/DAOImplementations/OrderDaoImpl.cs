using Application.DAOInterfaces;
using Shared.Models;

namespace GprcClients.DAOImplementations;

public class OrderDaoImpl : IOrderDao
{
    private OfferService.OfferServiceClient offerService;

    /// <summary>
    /// Initializes the OrderDaoImpl with the given gRPC client service
    /// </summary>
    /// <param name="offerService"></param>
    public OrderDaoImpl(OfferService.OfferServiceClient offerService)
    {
        this.offerService = offerService;
    }
    
    public Task CreateOrderOffersAsync(List<Shared.Models.OrderOffer> order)
    {
        throw new NotImplementedException();
    }

    public Task<IEnumerable<Shared.Models.Order>> GetAllOrdersAsync(string Username)
    {
        throw new NotImplementedException();
    }

    public Task CreateOrdersAsync(IEnumerable<Shared.Models.Order> orders)
    {
        throw new NotImplementedException();
    }

    public Task<IEnumerable<Shared.Models.OrderOffer>> GetOrdersOffersAsync(string username)
    {
        throw new NotImplementedException();
    }
}