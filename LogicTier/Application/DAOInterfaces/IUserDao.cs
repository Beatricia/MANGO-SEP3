using Shared.Models;

namespace Application.DAOInterfaces;

public interface IUserDao
{
    Task<Customer> GetCustomer(String username);
    Task<Customer> RegisterCustomer(Customer customer);
    Task<Farmer> RegisterFarmer(Farmer farmer);
}