namespace Shared.Models;

public class User
{
    public string Username { get; set; }
    public string Firstname { get; set; }
    public string Lastname { get; set; }

    public User(string username)
    {
        this.Username = username;
    }
}