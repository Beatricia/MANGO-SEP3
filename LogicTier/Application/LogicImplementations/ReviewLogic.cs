using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Shared.DTOs;
using Shared.Models;

namespace Application.LogicImplementations;

public class ReviewLogic : IReviewLogic
{
    private IReviewDao reviewDao;
    private IFarmDao farmDao;
    
    public ReviewLogic(IReviewDao reviewDao, IFarmDao farmDao)
    {
        this.reviewDao = reviewDao;
        this.farmDao = farmDao;
    }
    
    public async Task<Review> CreateReviewAsync(string farmName, string username, ReviewCreationDto reviewCreationDto)
    {
        ValidateReviewText(reviewCreationDto.ReviewText);
        
        var farm = await farmDao.GetFarmByNameAsync(farmName);
        
        if(farm is null)
            throw new Exception("Farm with name " + farmName + " does not exist");

        var review = new Review
        {
            FarmName = farmName,
            Date = DateTime.Now,
            Content = reviewCreationDto.ReviewText,
            WrittenBy = username
        };
        
        return await reviewDao.CreateReviewAsync(review);
    }

    public async Task<ICollection<Review>> GetAllReviewsByFarmAsync(string farmName)
    {
        var farm = await farmDao.GetFarmByNameAsync(farmName);
        
        if(farm is null)
            throw new Exception("Farm with name " + farmName + " does not exist");

        var result = await reviewDao.GetReviewsByFarmAsync(farm);
        return result;
    }

    public async Task<Review> EditReviewAsync(UpdateReviewDto review)
    {
        ValidateReviewText(review.UpdatedReview);
        
        
        var reviewToEdit = await reviewDao.GetReviewByIdAsync(review.Id);
        
        if(reviewToEdit.WrittenBy != review.Username)
            throw new Exception("This is not your review");
        
        reviewToEdit.Content = review.UpdatedReview;
        return await reviewDao.UpdateReviewAsync(reviewToEdit);
    }

    private void ValidateReviewText(string text)
    {
        if(text is {Length: 0})
            throw new Exception("Review text cannot be empty");
    }
}