using Shared.Models;

namespace Shared.DTOs;

public class OfferCreationDto
{
    public OfferCreationDto(){}
    
    public string FarmName { get; set; }

    public string Name { get; set; }

    public int Quantity { get; set; }
    
    public string Unit { get; set; }

    public double Price { get; set; }

    public CollectionOption CollectionOption { get; set; }
    public string? Description { get; set; }
    
    
}