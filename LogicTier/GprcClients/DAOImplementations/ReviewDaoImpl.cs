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

    public async Task<Shared.Models.Review> UpdateReviewAsync(Shared.Models.Review review)
    {
       var grpcReview = await client.EditReviewAsync(review.ToGrpc());
       return grpcReview.ToShared();
    }

    public async Task<ICollection<Shared.Models.Review>> GetReviewsByFarmAsync(Shared.Models.Farm farm)
    {
        Reviews grpcReviews =await  client.GetReviewsByFarmAsync(farm.ToGrpc());
        ICollection<Shared.Models.Review> list = new List<Shared.Models.Review>();

            foreach (var review in grpcReviews.Reviews_)
            {
                if (review is null)
                {
                    continue;
                }

                Shared.Models.Review reviewToSend = review.ToShared();
                list.Add(reviewToSend);
            }
            return list;
    }

    public async Task<Shared.Models.Review> GetReviewByIdAsync(long id)
    {
        var grpcReview = await client.GetReviewByIdAsync(id.ToGrpc());
        return grpcReview.ToShared();
    }
}