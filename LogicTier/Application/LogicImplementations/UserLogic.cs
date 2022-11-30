using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Shared.DTOs;
using Shared.Models;

namespace Application.LogicImplementations;

public class UserLogic : IUserLogic
{
    private readonly IUserDao userDao;

    public UserLogic(IUserDao userDao)
    {
        this.userDao = userDao;
    }

    public async Task<Customer?> GetCustomer(string username)
    {
        return await userDao.GetCustomer(username);
    }

    public async Task UpdateCustomerAsync(CustomerUpdateDto dto, string username)
    {
        await userDao.UpdateCustomerAsync(dto,username);
    }
}