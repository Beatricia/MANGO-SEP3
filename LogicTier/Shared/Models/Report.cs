namespace Shared.Models;

public class Report
{
    public long Id { get; set; }
    public Offer Offer { get; set; }
    public string Reason { get; set; }
    public Customer? Customer { get; set; }
}