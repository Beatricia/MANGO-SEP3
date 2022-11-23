using Shared.Models;

namespace HttpClient.ClientInterfaces;

public interface IAuthService
{
    Task<User> LoginAsync(string username, string password);
    Task<User> RegisterAsync(string username, string password, bool isFarmer);
}