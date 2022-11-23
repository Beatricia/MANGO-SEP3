package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOInterfaces.UserDaoInterface;
import mango.sep3.databaseaccess.protobuf.Text;
import mango.sep3.databaseaccess.protobuf.User;
import mango.sep3.databaseaccess.protobuf.UserAuth;
import mango.sep3.databaseaccess.protobuf.UserServiceGrpc;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    private UserDaoInterface userDao;


    public UserServiceImpl() {
    }


    @Override
    public void getUserByUsername(Text request, StreamObserver<User> responseObserver) {
        mango.sep3.databaseaccess.Shared.User user = userDao.getUserByUsername(request.getText());

        if(user == null){
            responseObserver.onError(new Exception("User not found"));
            return;
        }

        User userResponse = convertToGrpc(user);

        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void registerUser(UserAuth request, StreamObserver<User> responseObserver) {

        mango.sep3.databaseaccess.Shared.UserAuth userAuth = new mango.sep3.databaseaccess.Shared.UserAuth();
        userAuth.setUsername(request.getUsername());
        userAuth.setHash(request.getHash());
        userAuth.setSalt(request.getSalt());


        mango.sep3.databaseaccess.Shared.User user = userDao.registerUser(userAuth);

        // create gRPC user
        User userResponse = convertToGrpc(user);

        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();
    }

    private User convertToGrpc(mango.sep3.databaseaccess.Shared.User user) {
        return User.newBuilder()
                .setUsername(user.getUsername())
                .setFirstname(user.getFirstName())
                .setLastname(user.getLastName())
                .build();
    }
}
