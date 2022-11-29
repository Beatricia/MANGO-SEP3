package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.Order;
import mango.sep3.databaseaccess.Shared.OrderOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OrderOfferRepository extends JpaRepository<OrderOffer, Integer>
{
  Collection<OrderOffer> findAllByUsername(String username);
  @Modifying
  @Query("update OrderOffer orderOffer set orderOffer.order = :order where orderOffer.id = :id")
  void updateOrder(@Param(value ="id") int id, @Param(value = "order") Order order);
}
