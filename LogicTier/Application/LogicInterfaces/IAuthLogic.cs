using Shared.DTOs;
using Shared.Models;

namespace Application.LogicInterfaces;

public interface IAuthLogic
{
    Task<User> RegisterAsync(RegisterDto dto);
    
}