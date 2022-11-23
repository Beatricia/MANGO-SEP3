using Shared.Models;

namespace Shared.DTOs;

public class LoginResponse
{
    public string Token { get; set; } = "";
    public User User { get; set; }
}