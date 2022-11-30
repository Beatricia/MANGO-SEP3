package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.NotificationDaoInterface;
import mango.sep3.databaseaccess.Repositories.NotificationCustomerRepository;
import mango.sep3.databaseaccess.Repositories.NotificationFarmerRepository;
import mango.sep3.databaseaccess.Shared.NotificationCustomer;
import mango.sep3.databaseaccess.Shared.NotificationFarmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class NotificationDao implements NotificationDaoInterface {

    @Autowired
    private NotificationFarmerRepository notificationRepository;

    @Autowired
    private NotificationCustomerRepository notificationCustomerRepository;

    public NotificationDao(){}


    @Override
    public void addNotificationsFarmer(Collection<NotificationFarmer> notification) {
        notificationRepository.saveAll(notification);
    }

    @Override
    public void addNotificationsCustomer(Collection<NotificationCustomer> notification) {
        notificationCustomerRepository.saveAll(notification);
    }

    @Override
    public Collection<NotificationFarmer> getNotificationsFarmer(String username) {
        return notificationRepository.findByFarmerUsername(username);
    }

    @Override
    public Collection<NotificationCustomer> getNotificationsCustomer(String username) {
        return notificationCustomerRepository.findByCustomerUsername(username);
    }
}
