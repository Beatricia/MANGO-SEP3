package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.NotificationCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface NotificationCustomerRepository  extends JpaRepository<NotificationCustomer,Long> {
    Collection<NotificationCustomer> findByCustomerUsername(String username);
}
