package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.NotificationFarmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface NotificationFarmerRepository extends JpaRepository<NotificationFarmer,Long> {
    Collection<NotificationFarmer> findByFarmerUsername(String username);
}
