using Application.DAOInterfaces;
using GprcClients.Converters;

namespace GprcClients.DAOImplementations;

public class ReviewDaoImpl : IReviewDao
{
    private ReviewService.ReviewServiceClient client;
    
    public ReviewDaoImpl(ReviewService.ReviewServiceClient client)
    {
        this.client = client;
    }

    public Task<List<Shared.Models.Review>> GetReviewsByOfferIdAsync(string farmName)
    {
        throw new NotImplementedException();
    }

    public async Task<Shared.Models.Review> CreateReviewAsync(Shared.Models.Review review)
    {
        try
        {
            var grpcReview = await client.CreateReviewAsync(review.ToGrpc());
            return grpcReview.ToShared();
        }
        catch
        {
            throw new Exception("Review already exist");
        }
    }

    public Task<Shared.Models.Review> UpdateReviewAsync(Shared.Models.Review review)
    {
        throw new NotImplementedException();
    }
}