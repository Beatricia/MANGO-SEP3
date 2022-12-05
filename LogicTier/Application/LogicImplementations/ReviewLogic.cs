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
    
    public async Task<Review> CreateReview(string farmName, string username, ReviewCreationDto reviewCreationDto)
    {
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
}