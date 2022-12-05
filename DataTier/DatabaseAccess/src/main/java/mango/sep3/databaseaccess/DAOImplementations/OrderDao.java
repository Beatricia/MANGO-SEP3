package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.OrderDaoInterface;
import mango.sep3.databaseaccess.Repositories.*;
import mango.sep3.databaseaccess.Shared.*;
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
  private FarmerRepository farmerRepository;
  private  FarmRepository farmRepository;

  @Autowired
  public OrderDao(OrderRepository orderRepository, OrderOfferRepository orderOfferRepository,
      CustomerRepository customerRepository, CartRepository cartRepository, FarmerRepository farmerRepository, FarmRepository farmRepository)
  {
    this.orderRepository = orderRepository;
    this.orderOfferRepository = orderOfferRepository;
    this.customerRepository = customerRepository;
    this.cartRepository = cartRepository;
    this.farmerRepository = farmerRepository;
    this.farmRepository = farmRepository;
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
    List<Integer> order_ids = new ArrayList<>();
    Collection<Order> orders = new ArrayList<>();

    System.out.println("username: " + username);
    if (customerRepository.existsById(username))
    {

      Collection<OrderOffer> orderOffers = orderOfferRepository.findAllByUsername(username);

      for (OrderOffer orderOffer: orderOffers)
      {
        order_ids.add(orderOffer.getOrder().getId());
      }

      orders =  orderRepository.findAllById(order_ids);
    }
    else if (farmerRepository.existsById(username))
    {

      Farmer farmer = farmerRepository.findById(username).orElse(null);
      Collection<Farm> farms = farmRepository.findAllByFarmer(farmer);

      for(Farm farm: farms)
      {
        System.out.println("farm name: " + farm.getName());
        Collection<Order> farmsOrders = orderRepository.findAllByFarmName(farm.getName());
        System.out.println("Number of orders " + farmsOrders.size());
        orders.addAll(farmsOrders);
      }
      System.out.println("Orders size: " + orders.size());

    }


    //ToDo find better way to get only not done orders

    Collection<Order> notDoneOrders = new ArrayList<>();
    for (Order order:orders)
    {
      if (!order.isDone())
      {
        notDoneOrders.add(order);
      }
    }
    return notDoneOrders;
  }

  @Override
  public void deleteOrder(int id) {
    //orderOfferRepository.deleteAllByOrder(orderRepository.findById(id).orElse(null));
    orderRepository.deleteById(id);
  }

  @Override public void completeOrder(int id)
  {
    Order order = orderRepository.findById(id).orElse(null);
    order.setDone(true);
    orderRepository.saveAndFlush(order);
  }

  @Override public Collection<String> getUsersWithUncompletedOrder(Collection<Order> orders)
  {
    Collection<String> usernames = new ArrayList<>();
    Collection<OrderOffer> orderOffersFromOrder = new ArrayList<>();
    for (var orderOffer: orders)
    {
      orderOffersFromOrder.addAll(orderOfferRepository.findAllByOrder(orderOffer));
    }
    for (var orderOfferUser: orderOffersFromOrder)
    {
      String username = orderOfferUser.getUsername();
      if (!usernames.contains(username))
      {
        usernames.add(username);
      }
    }
    return usernames;
  }

  @Override public Order getOrderById(int id)
  {
    return orderRepository.findById(id).orElse(null);
  }

  private void createOrderOffersWithOrder(Order order)
  {
    Customer customer = customerRepository.findById(order.getUsername()).orElse(null);
    Collection<CartItem> cartItemSet = cartRepository.findAllByCustomerAndCollectionOption(customer, order.getCollectionOption());

    Collection<CartItem> cartItems = new ArrayList<>();
    for (var cartItem: cartItemSet)
    {
      if (cartItem.getOfferId().getFarm().getName().equals(order.getFarmName()))
      {
        cartItems.add(cartItem);
      }
    }

    for (var cartOffer : cartItems)
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
    cartRepository.deleteAll(cartItems);
  }
}
