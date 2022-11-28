namespace Shared.Models;

public class Customer : User
{
    public string Phone { get; set; } = "";
    public Address Address { get; set; } = new ();
}