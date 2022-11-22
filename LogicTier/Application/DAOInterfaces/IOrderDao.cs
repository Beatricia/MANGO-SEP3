using Shared.Models;

namespace Application.DAOInterfaces;

public interface IOrderDao
{
    Task CreateOrderOffersAsync(List<OrderOffer> order);
    Task<IEnumerable< Order>> GetAllOrdersAsync(string Username);
    Task CreateOrdersAsync(IEnumerable<Order> orders);
    Task<IEnumerable<OrderOffer>> GetOrdersOffersAsync(string username);
}