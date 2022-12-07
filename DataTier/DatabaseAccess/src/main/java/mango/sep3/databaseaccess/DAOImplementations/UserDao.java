package mango.sep3.databaseaccess.DAOImplementations;

import io.grpc.internal.DnsNameResolver;
import mango.sep3.databaseaccess.DAOInterfaces.UserDaoInterface;
import mango.sep3.databaseaccess.Repositories.*;
import mango.sep3.databaseaccess.Shared.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.zip.ZipEntry;

/**
 * Data access object accessing user related data.
 */
@Repository
public class UserDao implements UserDaoInterface {
    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FarmerRepository farmerRepository;
    @Autowired
    private AddressRepository addressRepository;

    public UserDao()
    { }

    @Override
    public User registerUser(UserAuth userAuth) {

        userAuthRepository.save(userAuth);

        var user = new User();
        user.setUsername(userAuth.getUsername());
        user.setFirstName("");
        user.setLastName("");

        userRepository.save(user);

        return user;
    }

    @Override
    public Farmer registerFarmer(Farmer farmer) {
        return farmerRepository.saveAndFlush(farmer);
    }

    @Override
    public Customer registerCustomer(Customer customer) {
        Address address = customer.getAddress();

        if (addressRepository.exists(Example.of(address))) {
            customer.setAddress(addressRepository.findAll(Example.of(address)).get(0));
        }
        else{
            addressRepository.saveAndFlush(address);
        }
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public User loginUser(UserAuth userAuth) {
        return userRepository.findById(userAuth.getUsername()).orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findById(username).orElse(null);
    }

    @Override
    public UserAuth getUserAuthByUsername(String username) {
        return userAuthRepository.findById(username).orElse(null);
    }



    @Override public Customer getCustomer(String username)
    {
        return customerRepository.findById(username).orElse(null);
    }

    @Override
    public Farmer getFarmer(String username) {
        return farmerRepository.findById(username).orElse(null);
    }

    @Override public void updateCustomer(String username, String phone,
        String city, String street, String zip, double latitude, double longitude)
    {
        Customer customer = customerRepository.findById(username).orElse(null);
        Address address = customer.getAddress();

        if (!phone.isEmpty())
        {
            customer.setPhone(phone);
            customerRepository.saveAndFlush(customer);
        }

        if (!city.isEmpty())
            address.setCity(city);
        if (!street.isEmpty())
            address.setStreet(street);
        if (!zip.isEmpty())
            address.setZip(zip);

        if(latitude != -Double.MAX_VALUE && longitude != -Double.MAX_VALUE){
            address.setLatitude(latitude);
            address.setLongitude(longitude);
        }

        addressRepository.saveAndFlush(address);

    }
}
