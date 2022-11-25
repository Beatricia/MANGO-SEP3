namespace Shared.Models;

public class Offer
{
    public int Id { get; set; }
    public string Name { get; set; }

    public int Quantity { get; set; }

    public string Unit { get; set; }
    public double Price { get; set; }

    public bool Delivery { get; set; }

    public bool PickUp { get; set; }
    
    public bool PickYourOwn { get; set; }
    public string? Description { get; set; }

    public Image Image { get; set; }

    public Offer()
    {
    }
}