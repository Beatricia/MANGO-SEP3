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
    private NotificationFarmerRepository notificationFarmerRepository;

    @Autowired
    private NotificationCustomerRepository notificationCustomerRepository;

    public NotificationDao(){}


    @Override
    public void addNotificationsFarmer(Collection<NotificationFarmer> notification) {
        notificationFarmerRepository.saveAll(notification);
    }

    @Override
    public void addNotificationsCustomer(Collection<NotificationCustomer> notification) {
        notificationCustomerRepository.saveAll(notification);
    }

    @Override
    public Collection<NotificationFarmer> getNotificationsFarmer(String username) {
        return notificationFarmerRepository.findByFarmerUsername(username);
    }

    @Override
    public Collection<NotificationCustomer> getNotificationsCustomer(String username) {
        return notificationCustomerRepository.findByCustomerUsername(username);
    }

    @Override
    public void deleteNotificationFarmer(NotificationFarmer notificationFarmer) {
        notificationFarmerRepository.delete(notificationFarmer);
    }

    @Override
    public void deleteNotificationCustomer(NotificationCustomer notificationCustomer) {
        notificationCustomerRepository.delete(notificationCustomer);
    }
}
