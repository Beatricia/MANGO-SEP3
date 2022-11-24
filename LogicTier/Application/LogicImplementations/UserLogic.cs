using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Shared.Models;

namespace Application.LogicImplementations;

public class UserLogic : IUserLogic
{
    private IUserDao userDao;

    public UserLogic(IUserDao userDao)
    {
        this.userDao = userDao;
    }

    public Task<Customer> GetCustomer(string username)
    {
        return userDao.GetCustomer(username);
    }
}