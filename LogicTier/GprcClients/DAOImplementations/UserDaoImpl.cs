using Application.DAOInterfaces;
using Shared.Models;

namespace GprcClients.DAOImplementations;

public class UserDaoImpl : IUserDao
{
    public Task<Shared.Models.Customer> GetCustomer(string username)
    {
        throw new NotImplementedException();
    }
}