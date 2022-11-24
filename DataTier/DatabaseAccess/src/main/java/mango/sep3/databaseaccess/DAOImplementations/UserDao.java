package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.UserDaoInterface;
import mango.sep3.databaseaccess.Repositories.UserAuthRepository;
import mango.sep3.databaseaccess.Repositories.UserRepository;
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
    public User loginUser(UserAuth userAuth) {
        UserAuth userToCheck = userAuthRepository.getReferenceById(userAuth.getUsername());

        if (userToCheck.equals(userAuth))
            return userRepository.getReferenceById(userAuth.getUsername());

        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findById(username).orElse(null);
    }


}
