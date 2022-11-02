namespace Shared.DTOs;

public class OfferCreationDto
{
    public OfferCreationDto(string name, int quantity, string unit, double price, bool delivery, bool pickUp, bool pickYourOwn, string? description, string imagePath)
    {
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

    public OfferCreationDto(string name, int quantity, string unit, double price, bool delivery, bool pickUp, bool pickYourOwn, string imagePath)
    {
        Name = name;
        Quantity = quantity;
        Unit = unit;
        Price = price;
        Delivery = delivery;
        PickUp = pickUp;
        PickYourOwn = pickYourOwn;
        ImagePath = imagePath;
    }

    public string Name { get; set; }

    public int Quantity { get; set; }
    
    public string Unit { get; set; }

    public double Price { get; set; }

    public bool Delivery { get; set; }

    public bool PickUp { get; set; }
    
    public bool PickYourOwn { get; set; }
    public string? Description { get; set; }

    public string ImagePath { get; set; }
    
    
}