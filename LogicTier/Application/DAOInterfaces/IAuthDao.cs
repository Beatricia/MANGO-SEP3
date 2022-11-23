using Shared.Models;

namespace Application.DAOInterfaces;

/// <summary>
/// Data access for authentication and user related objects
/// </summary>
public interface IAuthDao
{
    /// <summary>
    /// Register a user into the system
    /// </summary>
    /// <param name="user">user to register</param>
    /// <returns></returns>
    Task<User> RegisterAsync(UserAuth user);
    
    /// <summary>
    /// Get an authenticated user if exists, else null
    /// </summary>
    /// <param name="username">username of the user to get</param>
    /// <returns></returns>
    Task<UserAuth?> GetAuthUserAsync(string username);
    /// <summary>
    /// Get a user by username if exists, else null
    /// </summary>
    /// <param name="username">username of the user to get</param>
    /// <returns></returns>
    Task<User?> GetUserAsync(string username);
}