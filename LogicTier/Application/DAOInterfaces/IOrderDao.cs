using Shared.Models;

namespace Application.DAOInterfaces;

public interface IOrderDao
{
    Task<IEnumerable< Order>> GetAllOrdersAsync(string Username);
    Task CreateOrdersAsync(IEnumerable<Order> orders);
    Task CompleteOrderAsync(int id);
    Task DeleteOrderAsync(int id);
    Task<Order> GetOrderAsync(int orderId);
}