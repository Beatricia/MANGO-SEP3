using Shared.Models;

namespace Application.DAOInterfaces;

public interface IUserDao
{
    Task<Customer> GetCustomer(String username);
}