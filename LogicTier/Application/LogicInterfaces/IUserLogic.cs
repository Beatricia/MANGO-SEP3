using Shared.Models;

namespace Application.LogicInterfaces;

public interface IUserLogic
{
    Task<Customer> GetCustomer(String username);
}