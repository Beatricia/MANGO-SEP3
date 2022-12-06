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
    public Task<Review> CreateReviewAsync(string farmName, string username, ReviewCreationDto reviewCreationDto);

    Task<ICollection<Review>> GetAllReviewsByFarmAsync(string farmName);

    /// <summary>
    /// Edits an already existing review's text
    /// </summary>
    /// <param name="review"></param>
    /// <returns></returns>
    public Task<Review> EditReviewAsync(UpdateReviewDto review);
}