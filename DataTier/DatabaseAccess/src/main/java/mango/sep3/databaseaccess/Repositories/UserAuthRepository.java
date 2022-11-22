package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth,String> {
}
