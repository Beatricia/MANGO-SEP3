package mango.sep3.databaseaccess.DAOInterfaces;

import mango.sep3.databaseaccess.Shared.User;
import mango.sep3.databaseaccess.Shared.UserAuth;

public interface UserDaoInterface {
    User registerUser(UserAuth userAuth);
    User getUserByUsername(String username);
}
