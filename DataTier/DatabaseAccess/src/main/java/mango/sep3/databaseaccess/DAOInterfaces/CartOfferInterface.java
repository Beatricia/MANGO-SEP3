package mango.sep3.databaseaccess.DAOInterfaces;

import mango.sep3.databaseaccess.Shared.CartItem;
import mango.sep3.databaseaccess.Shared.User;

import java.util.Collection;

public interface CartOfferInterface
{
  void createCartOffer(CartItem cartItem);
  Collection<CartItem> getAllCartOffers(User username);
  void deleteAllCartOffers(User username);
}
