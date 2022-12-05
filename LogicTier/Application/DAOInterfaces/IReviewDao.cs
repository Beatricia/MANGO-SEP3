using Shared.Models;

namespace Application.DAOInterfaces;

public interface IReviewDao
{
    /// <summary>
    /// Get reviews by offer id
    /// </summary>
    /// <param name="offerId"></param>
    /// <returns></returns>
    List<Review> GetReviewsByOfferId(int offerId);
    
    /// <summary>
    /// Creates a review for an offer
    /// </summary>
    /// <param name="review"></param>
    /// <returns></returns>
    Review CreateReview(Review review);
    
    /// <summary>
    /// Updates a review for an offer
    /// </summary>
    /// <param name="review"></param>
    /// <returns></returns>
    Review UpdateReview(Review review);
}