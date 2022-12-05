package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByCustomerUsernameAndFarmName(String customerUsername, String farmName);
}
