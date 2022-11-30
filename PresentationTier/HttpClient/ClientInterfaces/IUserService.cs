using Shared.DTOs;
using Shared.Models;

namespace HttpClient.ClientInterfaces;

public interface IUserService
{
    Task<Farmer> GetFarmer(string username);
    Task<Customer> GetCustomer(string username);
    Task UpdateCustomerAsync(CustomerUpdateDto dto);
}