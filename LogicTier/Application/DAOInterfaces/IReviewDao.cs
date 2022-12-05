using Shared.Models;

namespace Application.DAOInterfaces;

public interface IReviewDao
{
    /// <summary>
    /// Get reviews by offer id
    /// </summary>
    /// <param name="offerId"></param>
    /// <returns></returns>
    Task<List<Review>> GetReviewsByOfferIdAsync(string farmName);
    
    /// <summary>
    /// Creates a review for an offer
    /// </summary>
    /// <param name="review"></param>
    /// <returns></returns>
    Task<Review> CreateReviewAsync(Review review);
    
    /// <summary>
    /// Updates a review for an offer
    /// </summary>
    /// <param name="review"></param>
    /// <returns></returns>
    Task<Review> UpdateReviewAsync(Review review);
}