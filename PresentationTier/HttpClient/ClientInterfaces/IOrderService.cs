using Shared.Models;

namespace HttpClient.ClientInterfaces;

public interface IOrderService
{
    Task CreateOrderAsync();
    Task<ICollection<Order>> GetAllOrdersAsync();
}