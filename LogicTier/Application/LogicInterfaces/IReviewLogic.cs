using Shared.DTOs;
using Shared.Models;

namespace Application.LogicInterfaces;

public interface IReviewLogic
{
    /// <summary>
    /// Creates a review for an offer
    /// </summary>
    /// <param name="offerId"></param>
    /// <param name="username"></param>
    /// <param name="reviewCreationDto"></param>
    /// <returns></returns>
    public Task<Review> CreateReview(string farmName, string username, ReviewCreationDto reviewCreationDto);
}