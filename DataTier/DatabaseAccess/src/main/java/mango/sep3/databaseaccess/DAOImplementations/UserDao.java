package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.UserDaoInterface;
import mango.sep3.databaseaccess.Repositories.CustomerRepository;
import mango.sep3.databaseaccess.Repositories.FarmerRepository;
import mango.sep3.databaseaccess.Repositories.UserAuthRepository;
import mango.sep3.databaseaccess.Repositories.UserRepository;
import mango.sep3.databaseaccess.Shared.Customer;
import mango.sep3.databaseaccess.Shared.Farmer;
import mango.sep3.databaseaccess.Shared.User;
import mango.sep3.databaseaccess.Shared.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
