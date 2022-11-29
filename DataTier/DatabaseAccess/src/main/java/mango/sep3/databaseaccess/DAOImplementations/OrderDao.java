package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.OrderDaoInterface;
import mango.sep3.databaseaccess.Repositories.*;
import mango.sep3.databaseaccess.Shared.CartItem;
import mango.sep3.databaseaccess.Shared.Customer;
import mango.sep3.databaseaccess.Shared.Order;
import mango.sep3.databaseaccess.Shared.OrderOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Repository
public class OrderDao implements OrderDaoInterface
{
  private OrderRepository orderRepository;
  private OrderOfferRepository orderOfferRepository;
  private CustomerRepository customerRepository;
  private CartRepository cartRepository;

  @Autowired
  public OrderDao(OrderRepository orderRepository, OrderOfferRepository orderOfferRepository,
      CustomerRepository customerRepository, CartRepository cartRepository)
  {
    this.orderRepository = orderRepository;
    this.orderOfferRepository = orderOfferRepository;
    this.customerRepository = customerRepository;
    this.cartRepository = cartRepository;
  }

  @Override public void createOrderOffers(Collection<OrderOffer> orderOffers)
  {
    orderOfferRepository.saveAll(orderOffers);
  }

  @Override public Collection<OrderOffer> getOrderOffers(Order order)
  {
    return orderOfferRepository.findAllByOrder(order);
  }

  @Transactional  //we put this in order to be able to delete the cart items!
  @Override public void createOrders(Collection<Order> orders)
  {
    for (var order : orders)
    {
      order = orderRepository.saveAndFlush(order);//set up the object to have the generated id
      createOrderOffersWithOrder(order);
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


  private void createOrderOffersWithOrder(Order order)
  {
    Customer customer = customerRepository.findById(order.getUsername()).orElse(null);
    Collection<CartItem> cartItemSet = cartRepository.findAllByCustomer(customer);
    for (var cartOffer : cartItemSet)
    {
      //create order offer
      OrderOffer orderOffer = new OrderOffer();
      orderOffer.setOffer(cartOffer.getOfferId());
      orderOffer.setCollectionOption(cartOffer.getCollectionOption());
      orderOffer.setQuantity(cartOffer.getQuantity());
      orderOffer.setOrder(order);
      orderOffer.setUsername(order.getUsername());
      orderOfferRepository.saveAndFlush(orderOffer);

      //remove the cartItem from the order object
    }
    cartRepository.deleteAllByCustomer(customer);
  }
}
