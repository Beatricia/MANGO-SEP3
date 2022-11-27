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
    
}