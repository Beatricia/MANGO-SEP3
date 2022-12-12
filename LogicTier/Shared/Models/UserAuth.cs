namespace Shared.Models;

public class UserAuth
{
    /// <summary>
    /// Username of the user
    /// </summary>
    public string Username { get; set; } = string.Empty;
    /// <summary>
    /// Generated salt value for the user
    /// </summary>
    public string Salt { get; set; } = string.Empty;
    /// <summary>
    /// Hash of the user's password
    /// </summary>
    public string HashPassword { get; set; } = string.Empty;
}