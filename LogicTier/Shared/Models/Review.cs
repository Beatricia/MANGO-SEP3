namespace Shared.Models;

/// <summary>
/// Represents a review of an offer.
/// </summary>
public class Review
{
    /// <summary>
    /// Id of the offer the review was given for
    /// </summary>
    public int OfferId { get; set; }
    /// <summary>
    /// Username of the user who gave the review
    /// </summary>
    public string WrittenBy { get; set; } = "";
    /// <summary>
    /// Content of the review
    /// </summary>
    public string Content { get; set; } = "";
    /// <summary>
    /// The Time the review was published or updated
    /// </summary>
    public DateTime Date { get; set; }
}