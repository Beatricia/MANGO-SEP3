package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.OrderDaoInterface;
import mango.sep3.databaseaccess.Repositories.CustomerRepository;
import mango.sep3.databaseaccess.Repositories.OrderOfferRepository;
import mango.sep3.databaseaccess.Repositories.OrderRepository;
import mango.sep3.databaseaccess.Repositories.UserRepository;
import mango.sep3.databaseaccess.Shared.Customer;
import mango.sep3.databaseaccess.Shared.Order;
import mango.sep3.databaseaccess.Shared.OrderOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class OrderDao implements OrderDaoInterface
{
  private OrderRepository orderRepository;
  private OrderOfferRepository orderOfferRepository;
  private CustomerRepository customerRepository;

  @Autowired
  public OrderDao(OrderRepository orderRepository, OrderOfferRepository orderOfferRepository,CustomerRepository customerRepository)
  {
    this.orderRepository = orderRepository;
    this.orderOfferRepository = orderOfferRepository;
    this.customerRepository = customerRepository;
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
    for (Order order: orders)
    {
      Customer customer = customerRepository.findById(order.getOrderOffers().iterator().next().getUsername()).orElse(null);
      order.setUsername(customer);
    }
    orderRepository.saveAllAndFlush(orders);

    List<Order> ordersFromDatabase = orderRepository.findAllByUsername(orders.iterator().next().getUsername().getUsername());

    for (Order order:ordersFromDatabase)
    {
      for (OrderOffer offer: order.getOrderOffers())
      {
        orderOfferRepository.updateOrder(offer.getId(),order);
      }
    }
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
