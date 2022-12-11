package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOInterfaces.NotificationDaoInterface;
import mango.sep3.databaseaccess.Shared.NotificationCustomer;
import mango.sep3.databaseaccess.Shared.NotificationFarmer;
import mango.sep3.databaseaccess.protobuf.*;
import mango.sep3.databaseaccess.protobuf.Void;
import mango.sep3.databaseaccess.utils.GrpcConverter;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@GRpcService
public class NotificationServiceImpl extends NotificationServiceGrpc.NotificationServiceImplBase {

    @Autowired
    private NotificationDaoInterface notificationDao;
    @Autowired private GrpcConverter grpcConverter;

    @Override
    public void getNotifications(Text request, StreamObserver<Notification> responseObserver) {

        String username = request.getText();

        notificationDao.getNotificationsFarmer(username).forEach(notification -> {
            var protoNotification = grpcConverter.convertToGrpc(notification);
            responseObserver.onNext(protoNotification);
        });
        notificationDao.getNotificationsCustomer(username).forEach(notification -> {
            var protoNotification = grpcConverter.convertToGrpc(notification);
            responseObserver.onNext(protoNotification);
        });

        responseObserver.onCompleted();
    }


    @Override
    public void addNotifications(RepeatedNotification request, StreamObserver<Void> responseObserver) {
        var farmerNots = new ArrayList<NotificationFarmer>();
        var customerNots = new ArrayList<NotificationCustomer>();

        for (var notification : request.getNotificationsList()) {

            var notificationFarmer = grpcConverter.convertToSharedNF(notification);
            if(notificationFarmer != null){
                farmerNots.add(notificationFarmer);
                continue;
            }

            var notificationCustomer = grpcConverter.convertToSharedNC(notification);
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

    @Override
    public void deleteNotification(Notification request, StreamObserver<Void> responseObserver) {
        var notificationFarmer = grpcConverter.convertToSharedNF(request);
        var notificationCustomer = grpcConverter.convertToSharedNC(request);

        if(notificationFarmer != null){
            notificationDao.deleteNotificationFarmer(notificationFarmer);
        }
        else if(notificationCustomer != null){
            notificationDao.deleteNotificationCustomer(notificationCustomer);
        }

        responseObserver.onNext(Void.newBuilder().build());
        responseObserver.onCompleted();
    }
}
