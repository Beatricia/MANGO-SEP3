namespace Shared.Models;

public class Notification
{
    public long Id { get; set; }
    public string Text { get; set; } = "";
    public string ToUsername { get; set; } = "";
    public DateTime CreatedAt { get; set; } 
}