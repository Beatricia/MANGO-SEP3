namespace Shared.Models;

public class Farm
{
    
    public string Name { get; set; }
    public Address Address { get; set; }
    public string Phone { get; set; }
    public int DeliveryDistance { get; set;}
    public string? FarmStatus { get; set; }
    public Farmer Farmer { get; set; }
    public FarmIcon FarmIcon { get; set; }
}