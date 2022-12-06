using Shared.Models;

namespace Application.DAOInterfaces;

public interface IReviewDao
{
    
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

    Task<ICollection<Review>> GetReviewsByFarmAsync(Farm farm);
    
    /// <summary>
    /// Gets a review by its id
    /// </summary>
    /// <param name="id"></param>
    /// <returns></returns>
    Task<Review> GetReviewByIdAsync(long id);
}