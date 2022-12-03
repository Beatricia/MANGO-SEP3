namespace Shared.Models;

public class OrderOffer
{
    public int Id { get; set; }
    public Offer Offer { get; set; }
    public int Quantity { get; set; }
    public string Username { get; set; }
    public CollectionOption CollectionOption { get; set; }
    public string FarmName { get; set; }
    
}