using Shared.Models;

namespace Application.DAOInterfaces;

public interface IOrderDao
{
    Task CreateOrderAsync(List<OrderOffer> order);
    Task<List<Order>> GetAllOrdersAsync(string Username);
}