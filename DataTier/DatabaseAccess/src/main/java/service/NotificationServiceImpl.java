package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOInterfaces.NotificationDaoInterface;
import mango.sep3.databaseaccess.DAOInterfaces.UserDaoInterface;
import mango.sep3.databaseaccess.Shared.NotificationCustomer;
import mango.sep3.databaseaccess.Shared.NotificationFarmer;
import mango.sep3.databaseaccess.protobuf.*;
import mango.sep3.databaseaccess.protobuf.Void;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@GRpcService
public class NotificationServiceImpl extends NotificationServiceGrpc.NotificationServiceImplBase {

    @Autowired
    private NotificationDaoInterface notificationDao;

    @Autowired
    private UserDaoInterface userDao;



    @Override
    public void getNotifications(Text request, StreamObserver<Notification> responseObserver) {

        String username = request.getText();

        notificationDao.getNotificationsFarmer(username).forEach(notification -> {
            var protoNotification = convertToGrpc(notification);
            responseObserver.onNext(protoNotification);
        });
        notificationDao.getNotificationsCustomer(username).forEach(notification -> {
            var protoNotification = convertToGrpc(notification);
            responseObserver.onNext(protoNotification);
        });

        responseObserver.onCompleted();
    }


    @Override
    public void addNotifications(RepeatedNotification request, StreamObserver<Void> responseObserver) {
        var farmerNots = new ArrayList<NotificationFarmer>();
        var customerNots = new ArrayList<NotificationCustomer>();

        for (var notification : request.getNotificationsList()) {

            var notificationFarmer = convertToSharedFarmerNotification(notification);
            if(notificationFarmer != null){
                farmerNots.add(notificationFarmer);
                continue;
            }

            var notificationCustomer = convertToSharedCustomerNotification(notification);
            if(notificationCustomer != null){
                customerNots.add(notificationCustomer);
                continue;
            }

            responseObserver.onError(new Exception("Couldn't find user " + notification.getToUsername()));
            return;
        }

        notificationDao.addNotificationsCustomer(customerNots);
        notificationDao.addNotificationsFarmer(farmerNots);

        responseObserver.onNext(Void.newBuilder().build());
        responseObserver.onCompleted();
    }

    private Notification convertToGrpc(NotificationFarmer notification) {
        return Notification.newBuilder()
                .setToUsername(notification.getFarmer().getUsername())
                .setText(notification.getMessage())
                .setCreatedAt(notification.getCreatedAt())
                .build();
    }
    private Notification convertToGrpc(NotificationCustomer notification) {
        return Notification.newBuilder()
                .setToUsername(notification.getCustomer().getUsername())
                .setText(notification.getMessage())
                .setCreatedAt(notification.getCreatedAt())
                .build();
    }


    // convert from grpc to shared notifications
    private NotificationFarmer convertToSharedFarmerNotification(Notification notification) {
        var farmer = userDao.getFarmer(notification.getToUsername());
        if(farmer == null) {
            return null;
        }

        var not = new NotificationFarmer();
        not.setMessage(notification.getText());
        not.setFarmer(farmer);
        not.setCreatedAt(notification.getCreatedAt());

        return not;
    }

    private NotificationCustomer convertToSharedCustomerNotification(Notification notification) {
        var customer = userDao.getCustomer(notification.getToUsername());
        if(customer == null) {
            return null;
        }

        var not = new NotificationCustomer();
        not.setMessage(notification.getText());
        not.setCustomer(customer);
        not.setCreatedAt(notification.getCreatedAt());

        return not;
    }
}
