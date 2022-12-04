package mango.sep3.databaseaccess.DAOInterfaces;

import mango.sep3.databaseaccess.Shared.Customer;
import mango.sep3.databaseaccess.Shared.Farmer;
import mango.sep3.databaseaccess.Shared.User;
import mango.sep3.databaseaccess.Shared.UserAuth;

/**
 * Data access object accessing user related data.
 */
public interface UserDaoInterface {
    /**
     * Registers a user.
     * @param userAuth The user to register.
     * @return The registered user.
     */
    User registerUser(UserAuth userAuth);

    /**
     * Registers a farmer in the database
     * @param farmer The farmer to register.
     * @return The registered farmer.
     */
    Farmer registerFarmer(Farmer farmer);

    /**
     * Registers a customer in the database
     * @param customer The customer to register.
     * @return The registered customer.
     */
    Customer registerCustomer(Customer customer);

    /**
     * Checks for user in the database. If Exists -> Logs the user in
     * @param userAuth The user to login.
     * @return The logged-in user.
     */
    User loginUser(UserAuth userAuth);

    /**
     * Gets a user by username.
     * @param username The username of the user.
     * @return The user.
     */
    User getUserByUsername(String username);

    /**
     * Gets authorized user by username.
     * @param username The username of the authorized user.
     * @return The authorized user.
     */
    UserAuth getUserAuthByUsername(String username);



    /**
     * Gets a customer by username.
     * @param username The username of the customer
     * @return The customer.
     */
    Customer getCustomer(String username);

    /**
     * Gets a farmer by username.
     * @param username The username of the farmer.
     * @return The farmer.
     */
    Farmer getFarmer(String username);

    void updateCustomer(String username, String phone, String city, String street, String zip, double latitude, double longitude);
}
