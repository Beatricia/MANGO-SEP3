package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>
{

}
