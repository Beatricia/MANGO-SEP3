package mango.sep3.databaseaccess.DAOInterfaces;

import mango.sep3.databaseaccess.Shared.Farm;
import mango.sep3.databaseaccess.Shared.Review;

import java.util.Collection;

public interface ReviewDaoInterface {
    Review saveReview(Review review);
    Collection<Review> getReviewsByFarmAsync(Farm farm);
}
