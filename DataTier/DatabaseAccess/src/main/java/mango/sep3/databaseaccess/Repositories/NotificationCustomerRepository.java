package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.NotificationCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface NotificationCustomerRepository  extends JpaRepository<NotificationCustomer,Long> {
    Collection<NotificationCustomer> findByCustomerUsername(String username);
}
