using Shared.Models;

namespace Application.LogicInterfaces;

public interface IOrderLogic
{
    Task CreateOrderAsync(string Username);
    Task<List<Order>> GetAllOrders(string Username);
}