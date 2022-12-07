package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.Farm;
import mango.sep3.databaseaccess.Shared.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FarmRepository extends JpaRepository<Farm,Long> {
  Farm findByName(String name);

  Collection<Farm> findAllByFarmerAndIsDisabled(Farmer farmer, boolean isDisabled);

  Collection<Farm> findAllByIsDisabled(boolean isDisabled);
  Collection<Farm> findAllByNameContainingIgnoreCaseAndIsDisabled(String nameContains, boolean isDisabled);
}
