using Shared.DTOs;
using Shared.Models;

namespace Application.LogicInterfaces;

/// <summary>
/// Authentication logic
/// </summary>
public interface IAuthLogic
{
    /// <summary>
    /// Register a new user to the system 
    /// </summary>
    /// <param name="dto"></param>
    /// <returns></returns>
    Task<User> RegisterAsync(RegisterDto dto);
    
    /// <summary>
    /// Login a new user to the system
    /// </summary>
    /// <param name="dto"></param>
    /// <returns></returns>
    Task<User> LoginAsync(LoginDto dto);
    
    /// <summary>
    /// Registers the admin in the system
    /// </summary>
    /// <returns></returns>
    Task RegisterAdminAsync(string pass);

    /// <summary>
    /// Gets if the admin is registered
    /// </summary>
    /// <returns></returns>
    Task<bool> IsAdminRegistered();

}