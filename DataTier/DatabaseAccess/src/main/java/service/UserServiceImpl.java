package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOInterfaces.UserDaoInterface;
import mango.sep3.databaseaccess.protobuf.*;
import mango.sep3.databaseaccess.protobuf.Void;
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
    public void getUserAuthByUsername(Text request, StreamObserver<UserAuth> responseObserver) {
        mango.sep3.databaseaccess.Shared.UserAuth userAuth = userDao.getUserAuthByUsername(request.getText());

        if(userAuth == null){
            responseObserver.onError(new Exception("User not found"));
            return;
        }

        UserAuth userResponse = convertUserAuthToGrpc(userAuth);

        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();
    }
    @Override
    public void registerCustomer(Customer request, StreamObserver<Customer> responseObserver) {
        var customer = convertToShared(request);
        customer = userDao.registerCustomer(customer);

        responseObserver.onNext(convertToGrpc(customer));
        responseObserver.onCompleted();
    }

    @Override public void registerUser(UserAuth request,
        StreamObserver<UserAuth> responseObserver)
    {
        var user = convertUserAuthToShared(request);
        userDao.registerUser(user);

        responseObserver.onNext(request);
        responseObserver.onCompleted();
    }

    private mango.sep3.databaseaccess.Shared.UserAuth convertUserAuthToShared(UserAuth request)
    {
        var userAuth = new mango.sep3.databaseaccess.Shared.UserAuth();
        userAuth.setUsername(request.getUsername());
        userAuth.setHash(request.getHash());
        userAuth.setSalt(request.getSalt());
        return userAuth;
    }

    @Override
    public void registerFarmer(Farmer request, StreamObserver<Farmer> responseObserver) {
        var farmer = convertToShared(request);
        farmer = userDao.registerFarmer(farmer);

        responseObserver.onNext(convertToGrpc(farmer));
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

        Customer customerResponse = convertToGrpc(customer);

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

        Farmer customerResponse = convertToGrpc(farmer);

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


    private UserAuth convertUserAuthToGrpc(mango.sep3.databaseaccess.Shared.UserAuth user) {
        return UserAuth.newBuilder()
                .setUsername(user.getUsername())
                .setSalt(user.getSalt())
                .setHash(user.getHash())
                .build();
    }

    private Customer convertToGrpc(mango.sep3.databaseaccess.Shared.Customer customer)
    {
        Address address = Address.newBuilder()
            .setCity(customer.getAddress().getCity())
            .setStreet(customer.getAddress().getStreet())
            .setZip(customer.getAddress().getZip())
            .build();

        return Customer.newBuilder()
            .setUsername(customer.getUsername())
            .setFirstname(customer.getFirstName())
            .setLastname(customer.getLastName())
            .setAddress(address)
            .build();
    }

    // convert from shared to grpc farmer
    private Farmer convertToGrpc(mango.sep3.databaseaccess.Shared.Farmer farmer)
    {
        return Farmer.newBuilder()
            .setUsername(farmer.getUsername())
            .setFirstname(farmer.getFirstName())
            .setLastname(farmer.getLastName())
            .build();
    }


    private mango.sep3.databaseaccess.Shared.Farmer convertToShared(Farmer farmer)
    {
        mango.sep3.databaseaccess.Shared.Farmer sharedFarmer = new mango.sep3.databaseaccess.Shared.Farmer();
        sharedFarmer.setUsername(farmer.getUsername());
        sharedFarmer.setFirstName(farmer.getFirstname());
        sharedFarmer.setLastName(farmer.getLastname());

        return sharedFarmer;
    }

    // convert from grpc to shared customer
    private mango.sep3.databaseaccess.Shared.Customer convertToShared(Customer customer)
    {
        mango.sep3.databaseaccess.Shared.Customer sharedCustomer = new mango.sep3.databaseaccess.Shared.Customer();
        sharedCustomer.setUsername(customer.getUsername());
        sharedCustomer.setFirstName(customer.getFirstname());
        sharedCustomer.setLastName(customer.getLastname());

        mango.sep3.databaseaccess.Shared.Address address = new mango.sep3.databaseaccess.Shared.Address();
        address.setCity(customer.getAddress().getCity());
        address.setStreet(customer.getAddress().getStreet());
        address.setZip(customer.getAddress().getZip());

        sharedCustomer.setAddress(address);

        return sharedCustomer;
    }
}
