using Shared.Models;

namespace HttpClient.ClientInterfaces;

public interface IOrderService
{
    Task CreateOrderAsync(string username);
    Task<ICollection<Order>> GetAllOrdersAsync(string username);
}