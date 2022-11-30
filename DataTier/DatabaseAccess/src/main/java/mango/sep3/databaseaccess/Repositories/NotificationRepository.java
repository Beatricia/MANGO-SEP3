package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    Collection<Notification> findByToUsername(String username);
}
