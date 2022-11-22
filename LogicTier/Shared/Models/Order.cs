namespace Shared.Models;

public class Order
{
    public int Id { get; set; }
    public List<OrderOffer> OrderOffers { get; set; }
    public bool IsDone { get; set; }
    public string FarmName { get; set; }
    public string CollectionOption { get; set; }
}