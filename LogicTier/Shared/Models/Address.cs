namespace Shared.Models;

public class Address
{
    public string City { get; set; }
    
    public string Street { get; set; }
    
    public string ZIP { get; set; }
    
    public override string ToString()
    {
        return $"City: {City}, Zip: {ZIP}, Address: {Street} ";
    }
}