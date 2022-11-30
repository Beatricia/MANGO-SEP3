package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOInterfaces.NotificationDaoInterface;
import mango.sep3.databaseaccess.protobuf.*;
import mango.sep3.databaseaccess.protobuf.Void;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@GRpcService
public class NotificationServiceImpl extends NotificationServiceGrpc.NotificationServiceImplBase {

    @Autowired
    private NotificationDaoInterface notificationDao;



    @Override
    public void getNotifications(Text request, StreamObserver<Notification> responseObserver) {

        String username = request.getText();

        notificationDao.getNotifications(username).forEach(notification -> {
            var protoNotification = convertToGrpc(notification);
            responseObserver.onNext(protoNotification);
        });

        responseObserver.onCompleted();
    }


    @Override
    public void addNotifications(RepeatedNotification request, StreamObserver<Void> responseObserver) {
        var list = new ArrayList<mango.sep3.databaseaccess.Shared.Notification>();

        for (var notification : request.getNotificationsList()) {
            list.add(convertToShared(notification));
        }

        notificationDao.addNotifications(list);

        responseObserver.onNext(Void.newBuilder().build());
        responseObserver.onCompleted();
    }

    private Notification convertToGrpc(mango.sep3.databaseaccess.Shared.Notification notification) {
        return Notification.newBuilder()
                .setToUsername(notification.getToUsername())
                .setText(notification.getMessage())
                .setCreatedAt(notification.getCreatedAt())
                .build();
    }


    // convert from grpc to shared notifications
    private mango.sep3.databaseaccess.Shared.Notification convertToShared(Notification notification) {
        var not = new mango.sep3.databaseaccess.Shared.Notification();
        not.setMessage(notification.getText());
        not.setToUsername(notification.getToUsername());
        not.setCreatedAt(notification.getCreatedAt());

        return not;
    }
}
