package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.ReviewDaoInterface;
import mango.sep3.databaseaccess.Repositories.ReviewRepository;
import mango.sep3.databaseaccess.Shared.Review;
import org.springframework.stereotype.Repository;

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
}
