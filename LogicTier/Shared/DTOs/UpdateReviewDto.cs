namespace Shared.DTOs;

/// <summary>
/// Represents an object which is used to update a review
/// </summary>
public class UpdateReviewDto
{
    public long Id { get; set; }
    public string Username { get; set; } = "";
    public string UpdatedReview { get; set; } = "";
}