package mango.sep3.databaseaccess.DAOInterfaces;

import mango.sep3.databaseaccess.Shared.CartItem;
import mango.sep3.databaseaccess.Shared.Customer;
import mango.sep3.databaseaccess.Shared.Offer;

import java.util.Collection;
import java.util.Optional;

public interface CartOfferInterface
{
  void createCartOffer(CartItem cartItem);
  Collection<CartItem> getAllCartOffers(Customer customer);
  void deleteAllCartOffers(Customer customer);
  CartItem getById(int id);
  void deleteCartOffer(int id);
  void deleteAllByOfferId(Offer offerId);

  void updateCartOffer(int cartOfferId, int quantity);
}
