namespace Shared.Models;

public class CartOfferDto
{
    public int OfferId { get; set; }
    public int Quantity { get; set; }
    public string Username { get; set; }
    
    public CollectionOption CollectionOption { get; set; }
    
}