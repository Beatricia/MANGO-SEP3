namespace Shared.DTOs;

public class ReportCreationDto
{
    public int OfferId { get; set; }
    public string Reason { get; set; } = "";
    public string? ReportedByCustomerUsername { get; set; }
}