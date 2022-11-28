package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.OrderDaoInterface;
import mango.sep3.databaseaccess.Repositories.OrderOfferRepository;
import mango.sep3.databaseaccess.Repositories.OrderRepository;
import mango.sep3.databaseaccess.Shared.Order;
import mango.sep3.databaseaccess.Shared.OrderOffer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrderDao implements OrderDaoInterface
{
  private OrderRepository orderRepository;
  private OrderOfferRepository orderOfferRepository;

  @Autowired
  public OrderDao(OrderRepository orderRepository, OrderOfferRepository orderOfferRepository)
  {
    this.orderRepository = orderRepository;
    this.orderOfferRepository = orderOfferRepository;
  }

  @Override public void createOrderOffers(Collection<OrderOffer> orderOffers)
  {
    orderOfferRepository.saveAll(orderOffers);
  }

  @Override public Collection<OrderOffer> getOrderOffers(String username)
  {
    return orderOfferRepository.findAllByUsername(username);
  }

  @Override public void createOrders(Collection<Order> orders)
  {
    orderRepository.saveAll(orders);
  }

  @Override public Collection<Order> getAllOrders(String username)
  {

    Collection<OrderOffer> orderOffers = orderOfferRepository.findAllByUsername(username);

    List<Integer> order_ids = new ArrayList<>();

    for (var orderOffer: orderOffers)
    {
      order_ids.add(orderOffer.getOrder().getId());
    }
    return orderRepository.findAllById(order_ids);
  }
}