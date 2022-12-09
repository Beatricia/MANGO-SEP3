using Shared.DTOs;
using Shared.Models;

namespace Application.DAOInterfaces;

public interface IUserDao
{
    Task<Customer?> GetCustomer(string username);
    Task<Farmer?> GetFarmer(string username);
    Task<User?> GetAdmin(string username);
    Task<Customer> RegisterCustomer(Customer customer);
    Task<Farmer> RegisterFarmer(Farmer farmer);
    Task<Admin> RegisterAdmin();
    Task UpdateCustomerAsync(CustomerUpdateDto dto,string username);
    
}