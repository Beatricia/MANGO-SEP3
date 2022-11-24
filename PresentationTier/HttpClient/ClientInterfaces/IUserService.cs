using Shared.Models;

namespace HttpClient.ClientInterfaces;

public interface IUserService
{
    Task<Customer> GetCustomer(string username);
}