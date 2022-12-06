package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.Farm;
import mango.sep3.databaseaccess.Shared.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByCustomerUsernameAndFarmName(String customerUsername, String farmName);
    Collection<Review> findAllByFarm(Farm farm);
}
