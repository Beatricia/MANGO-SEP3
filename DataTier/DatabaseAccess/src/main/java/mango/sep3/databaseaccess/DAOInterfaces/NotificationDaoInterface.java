package mango.sep3.databaseaccess.DAOInterfaces;

import mango.sep3.databaseaccess.Shared.Notification;

import java.util.Collection;

public interface NotificationDaoInterface {
    void addNotifications(Collection<Notification> notification);
    Collection<Notification> getNotifications(String username);
}
