using Shared.Models;

namespace Application.DAOInterfaces;

public interface IOrderDao
{
    Task CreateOrderAsync(List<OrderOffer> order);
    Task<IEnumerable<Order>> GetAllOrdersAsync(string Username);
}