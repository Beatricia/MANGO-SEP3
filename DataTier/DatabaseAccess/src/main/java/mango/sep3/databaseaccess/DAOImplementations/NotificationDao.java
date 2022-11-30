package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.NotificationDaoInterface;
import mango.sep3.databaseaccess.Repositories.NotificationRepository;
import mango.sep3.databaseaccess.Shared.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class NotificationDao implements NotificationDaoInterface {

    @Autowired
    private NotificationRepository notificationRepository;

    public NotificationDao(){}


    @Override
    public void addNotifications(Collection<Notification> notification) {
        notificationRepository.saveAll(notification);
    }

    @Override
    public Collection<Notification> getNotifications(String username) {
        return notificationRepository.findByToUsername(username);
    }
}
