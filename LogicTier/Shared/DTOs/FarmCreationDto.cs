namespace Shared.DTOs;

public class FarmCreationDto
{
    public string Name { get; set; }

    public string City { get; set; }
    
    public string Address { get; set; }
    
    public string ZIP { get; set; }
    
    public string Phone { get; set; }
    
    public int DeliveryDistance { get; set; }
    
    public string? FarmStatus { get; set; }
}