using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Shared.DTOs;
using Shared.Models;

namespace Application.LogicImplementations;

public class UserLogic : IUserLogic
{
    private readonly IUserDao userDao;
    private IAddressDao addressDao;

    public UserLogic(IUserDao userDao, IAddressDao addressDao)
    {
        this.userDao = userDao;
        this.addressDao = addressDao;
    }

    public async Task<Customer?> GetCustomer(string username)
    {
        return await userDao.GetCustomer(username);
    }

    public async Task UpdateCustomerAsync(CustomerUpdateDto dto, string username)
    {
        Customer? customer = await userDao.GetCustomer(username);
        if(customer == null)
            throw new Exception("Customer not found");
        
        Address customerAddress = customer.Address;
        bool addressChanged = false;
        
        if(dto.City is not null && (addressChanged = true)) // (addressChanged = true) with only
            customerAddress.City = dto.City;                // one equal sign is intentional
        if(dto.Street is not null && (addressChanged = true))
            customerAddress.Street = dto.Street;
        if(dto.Zip is not null && (addressChanged = true))
            customerAddress.ZIP = dto.Zip;

        if (addressChanged)
        {
            (dto.Latitude, dto.Longitude) = await addressDao.GetCoordinatesAsync(customerAddress);
        }

        await userDao.UpdateCustomerAsync(dto,username);
    }

    public async Task<Farmer?> GetFarmer(string username)
    {
        return await userDao.GetFarmer(username);
    }
}