package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer,String> {
}
