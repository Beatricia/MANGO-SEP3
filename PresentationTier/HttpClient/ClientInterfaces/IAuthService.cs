using Shared.DTOs;
using Shared.Models;

namespace HttpClient.ClientInterfaces;

/// <summary>
/// Authentication service responsible for logging in and registering
/// </summary>
public interface IAuthService
{
    /// <summary>
    /// Get login information of the user
    /// </summary>
    /// <param name="username"></param>
    /// <param name="password"></param>
    /// <returns>LoginResponse containing the logged in user and the token</returns>
    Task<User> LoginAsync(string username, string password);
    
    /// <summary>
    /// Register a user
    /// </summary>
    /// <param name="username"></param>
    /// <param name="password"></param>
    /// <param name="isFarmer"></param>
    /// <returns></returns>
    Task<User> RegisterAsync(string username, string password, bool isFarmer);
}