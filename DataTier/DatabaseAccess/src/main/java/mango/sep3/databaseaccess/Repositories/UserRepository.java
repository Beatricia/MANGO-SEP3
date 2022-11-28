package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.Farmer;
import mango.sep3.databaseaccess.Shared.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
}
