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

    public string ImagePath { get; set; }

    public Offer()
    {
    }

    public Offer(int id,string name, int quantity, string unit,double price, bool delivery, bool pickUp, bool pickYourOwn, string? description, string imagePath)
    {
        Id = id;
        Name = name;
        Quantity = quantity;
        Unit = unit;
        Price = price;
        Delivery = delivery;
        PickUp = pickUp;
        PickYourOwn = pickYourOwn;
        Description = description;
        ImagePath = imagePath;
    }   
}