package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOInterfaces.UserDaoInterface;
import mango.sep3.databaseaccess.protobuf.*;
import mango.sep3.databaseaccess.protobuf.Void;
import mango.sep3.databaseaccess.utils.GrpcConverter;
import org.checkerframework.checker.units.qual.A;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    private UserDaoInterface userDao;

    @Autowired private GrpcConverter converter;


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
    public void getUserAuthByUsername(Text request, StreamObserver<UserAuth> responseObserver) {
        mango.sep3.databaseaccess.Shared.UserAuth userAuth = userDao.getUserAuthByUsername(request.getText());

        if(userAuth == null){
            responseObserver.onError(new Exception("User not found"));
            return;
        }

        UserAuth userResponse = converter.convertToGrpc(userAuth);

        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();
    }
    @Override
    public void registerCustomer(Customer request, StreamObserver<Customer> responseObserver) {
        var customer = converter.convertToShared(request);
        customer = userDao.registerCustomer(customer);

        responseObserver.onNext(converter.convertToGrpc(customer));
        responseObserver.onCompleted();
    }

    @Override public void registerUser(UserAuth request,
        StreamObserver<UserAuth> responseObserver)
    {
        var user = converter.convertToShared(request);
        userDao.registerUser(user);

        responseObserver.onNext(request);
        responseObserver.onCompleted();
    }


    @Override
    public void registerFarmer(Farmer request, StreamObserver<Farmer> responseObserver) {
        var farmer = converter.convertToShared(request);
        farmer = userDao.registerFarmer(farmer);

        responseObserver.onNext(converter.convertToGrpc(farmer));
        responseObserver.onCompleted();
    }

    @Override public void getCustomer(Text request,
                                      StreamObserver<Customer> responseObserver)
    {
        mango.sep3.databaseaccess.Shared.Customer customer = userDao.getCustomer(request.getText());

        if (customer == null)
        {
            responseObserver.onError(new Exception("Customer not found"));
            return;
        }

        Customer customerResponse = converter.convertToGrpc(customer);

        responseObserver.onNext(customerResponse);
        responseObserver.onCompleted();
    }

    private User convertToGrpc(mango.sep3.databaseaccess.Shared.User user) {
        return User.newBuilder()
                .setUsername(user.getUsername())
                .setFirstname(user.getFirstName())
                .setLastname(user.getLastName())
                .build();
    }

    @Override public void getFarmer(Text request,
        StreamObserver<Farmer> responseObserver)
    {
        mango.sep3.databaseaccess.Shared.Farmer farmer = userDao.getFarmer(request.getText());

        if (farmer == null)
        {
            responseObserver.onError(new Exception("Farmer not found"));
            return;
        }

        Farmer customerResponse = converter.convertToGrpc(farmer);

        responseObserver.onNext(customerResponse);
        responseObserver.onCompleted();
    }

    @Override public void updateCustomer(CustomerUpdate request,
        StreamObserver<Void> responseObserver)
    {
        userDao.updateCustomer(request.getUsername(),request.getPhone(),request.getCity(),request.getStreet(),request.getZip());

        responseObserver.onNext(Void.newBuilder().build());
        responseObserver.onCompleted();
    }

}
