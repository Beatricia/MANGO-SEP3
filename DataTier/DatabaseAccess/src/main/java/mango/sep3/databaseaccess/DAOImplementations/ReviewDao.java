package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.ReviewDaoInterface;
import mango.sep3.databaseaccess.Repositories.ReviewRepository;
import mango.sep3.databaseaccess.Shared.Farm;
import mango.sep3.databaseaccess.Shared.Review;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class ReviewDao implements ReviewDaoInterface {
    private ReviewRepository reviewRepository;

    public ReviewDao(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    @Override
    public Review saveReview(Review review) {

        if(reviewRepository.existsByCustomerUsernameAndFarmName(review.getCustomer().getUsername(), review.getFarm().getName())){
            throw new IllegalArgumentException("Review already exists");
        }

        return reviewRepository.save(review);
    }

    @Override public Collection<Review> getReviewsByFarmAsync(Farm farm)
    {
        System.out.println(reviewRepository.findAllByFarm(farm));
        return reviewRepository.findAllByFarm(farm);
    }

    @Override
    public Review editReview(Review review) {
        // we need to get the review with the correct id
        var reviewToEdit = reviewRepository.findByCustomerUsernameAndFarmName(
                review.getCustomer().getUsername(),
                review.getFarm().getName());

        if(reviewToEdit == null){
            throw new IllegalArgumentException("Review does not exist");
        }

        reviewToEdit.setReviewText(review.getReviewText());
        return reviewRepository.save(reviewToEdit);
    }

    @Override
    public Review getReviewById(long id) {
        return reviewRepository.findById(id).orElse(null);
    }
}
