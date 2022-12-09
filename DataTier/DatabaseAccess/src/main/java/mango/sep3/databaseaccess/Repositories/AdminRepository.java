package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String>
{
}
