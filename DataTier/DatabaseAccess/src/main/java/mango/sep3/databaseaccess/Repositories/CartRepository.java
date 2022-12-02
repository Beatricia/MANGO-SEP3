package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.CartItem;
import mango.sep3.databaseaccess.Shared.Customer;
import mango.sep3.databaseaccess.Shared.Offer;
import mango.sep3.databaseaccess.Shared.User;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository 
public interface CartRepository extends JpaRepository<CartItem,Integer>
{
  Collection<CartItem> findAllByCustomer(Customer username);
  Collection<CartItem> findAllByCustomerAndCollectionOption(Customer username, String collectionOption);
  void deleteAllByCustomer(Customer username);
}
