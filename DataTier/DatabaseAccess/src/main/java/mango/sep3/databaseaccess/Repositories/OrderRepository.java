package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>
{
 Collection<Order> findAllByFarmNameAndDoneIsFalse(String farmName);
}
