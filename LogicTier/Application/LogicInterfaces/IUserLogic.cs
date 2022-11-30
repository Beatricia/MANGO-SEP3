using Shared.DTOs;
using Shared.Models;

namespace Application.LogicInterfaces;

public interface IUserLogic
{
    Task<Customer> GetCustomer(String username);
    Task UpdateCustomerAsync(CustomerUpdateDto dto,string username);
    Task<Farmer> GetFarmer(string username);
}