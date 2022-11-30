package mango.sep3.databaseaccess.DAOInterfaces;

import mango.sep3.databaseaccess.Shared.NotificationCustomer;
import mango.sep3.databaseaccess.Shared.NotificationFarmer;

import java.util.Collection;

public interface NotificationDaoInterface {
    void addNotificationsFarmer(Collection<NotificationFarmer> notification);
    void addNotificationsCustomer(Collection<NotificationCustomer> notification);

    Collection<NotificationFarmer> getNotificationsFarmer(String username);
    Collection<NotificationCustomer> getNotificationsCustomer(String username);
}
