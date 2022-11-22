using Shared.Models;

namespace Application.DAOInterfaces;

public interface IAuthDao
{
    Task<User> RegisterAsync(UserAuth user);
    Task<UserAuth?> GetAuthUserAsync(string username);
}