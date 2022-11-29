package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>
{
  List<Order> findAllByUsername(String username);
}
