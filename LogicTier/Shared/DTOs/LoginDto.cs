namespace Shared.DTOs;

/// <summary>
/// Holds information required to log in
/// </summary>
public class LoginDto
{
    /// <summary>
    /// Username of the person who wants to log in
    /// </summary>
    public string Username { get; set; }
    /// <summary>
    /// Password of the person who wants to log in
    /// </summary>
    public string Password { get; set; }
}