package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOInterfaces.UserDaoInterface;
import mango.sep3.databaseaccess.protobuf.*;
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

    @Override
    public void registerCustomer(Customer request, StreamObserver<Customer> responseObserver) {
        var customer = convertToShared(request);
        customer = userDao.registerCustomer(customer);

        responseObserver.onNext(convertToGrpc(customer));
        responseObserver.onCompleted();
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
