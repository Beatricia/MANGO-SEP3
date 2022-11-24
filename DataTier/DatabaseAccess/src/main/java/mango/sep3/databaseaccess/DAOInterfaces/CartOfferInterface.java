package mango.sep3.databaseaccess.DAOInterfaces;

import mango.sep3.databaseaccess.Shared.CartItem;
import mango.sep3.databaseaccess.protobuf.CartOffer;

public interface CartOfferInterface
{
  void createCartOffer(CartItem cartItem);
}
