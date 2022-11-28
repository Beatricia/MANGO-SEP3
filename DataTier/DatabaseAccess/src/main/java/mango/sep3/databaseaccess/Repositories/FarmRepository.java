package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmRepository extends JpaRepository<Farm,Long> {
  Farm findByName(String name);
}
