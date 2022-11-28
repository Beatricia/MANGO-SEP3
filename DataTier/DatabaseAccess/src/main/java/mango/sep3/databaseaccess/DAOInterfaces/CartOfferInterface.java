package mango.sep3.databaseaccess.DAOInterfaces;

import mango.sep3.databaseaccess.Shared.CartItem;
import mango.sep3.databaseaccess.Shared.Customer;
import mango.sep3.databaseaccess.Shared.User;

import java.util.Collection;

public interface CartOfferInterface
{
  void createCartOffer(CartItem cartItem);
  Collection<CartItem> getAllCartOffers(Customer customer);
  void deleteAllCartOffers(Customer customer);
}
