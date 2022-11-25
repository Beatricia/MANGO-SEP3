package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.CartItem;
import mango.sep3.databaseaccess.Shared.Farm;
import mango.sep3.databaseaccess.Shared.User;
import mango.sep3.databaseaccess.protobuf.CartOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository 
public interface CartRepository extends JpaRepository<CartItem,Integer>
{
  Collection<CartOffer> findAllByUsername(User username);
  void deleteAllByUsername(User username);
}
