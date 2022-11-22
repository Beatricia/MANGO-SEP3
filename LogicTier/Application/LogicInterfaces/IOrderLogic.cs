using Shared.Models;

namespace Application.LogicInterfaces;

public interface IOrderLogic
{
    Task CreateOrderAsync(string Username);
    Task<IEnumerable<Order>> GetAllOrders(string Username);
}