using Shared.Models;

namespace Application.DAOInterfaces;

public interface IUserDao
{
    Task<Customer?> GetCustomer(string username);
    Task<Farmer?> GetFarmer(string username);
    Task<Customer> RegisterCustomer(Customer customer);
    Task<Farmer> RegisterFarmer(Farmer farmer);
}