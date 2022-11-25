namespace Shared.Models;

public class CartOffer
{
    public int Id { get; set; }
    public int OfferId { get; set; }
    public int Quantity { get; set; }
    public string UserName { get; set; }
    public string CollectionOption { get; set; }
}