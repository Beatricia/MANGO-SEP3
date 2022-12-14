package mango.sep3.databaseaccess.DAOInterfaces;

import mango.sep3.databaseaccess.Shared.Order;
import mango.sep3.databaseaccess.Shared.OrderOffer;

import java.util.Collection;


public interface OrderDaoInterface
{
  void createOrderOffers(Collection<OrderOffer> orderOffers);
  Collection<OrderOffer> getOrderOffers(Order order);
  void createOrders(Collection<Order> orders);
  Collection<Order> getAllOrders(String username);
  void deleteOrder(int id);
  void completeOrder(int id);
  Collection<String> getUsersWithUncompletedOrder(Collection<Order> orderIds);

  Order getOrderById(int id);
}
