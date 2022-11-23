namespace Shared.Models;

public class UserAuth
{
    public string Username { get; set; } = string.Empty;
    public string Salt { get; set; } = string.Empty;
    public string HashPassword { get; set; } = string.Empty;
}