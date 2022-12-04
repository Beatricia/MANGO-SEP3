namespace Shared.DTOs;

public class CustomerUpdateDto
{
 public string? Phone { get; set; }
 public string? City { get; set; }
 public string? Street { get; set; }
 public string? Zip { get; set; }
 public double? Longitude { get; set; }
 public double? Latitude { get; set; }
}