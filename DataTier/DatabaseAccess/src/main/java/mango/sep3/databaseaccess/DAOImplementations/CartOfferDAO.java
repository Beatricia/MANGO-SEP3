package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.CartOfferInterface;
import mango.sep3.databaseaccess.FileData.FileContext;
import mango.sep3.databaseaccess.Repositories.CartRepository;
import mango.sep3.databaseaccess.Shared.CartItem;
import mango.sep3.databaseaccess.Shared.User;
import mango.sep3.databaseaccess.protobuf.CartOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class CartOfferDAO implements CartOfferInterface
{
  private FileContext context;
  private CartRepository cartRepository;

  @Autowired
  public CartOfferDAO(CartRepository cartRepository){
    this.cartRepository = cartRepository;
  }

  @Override public void createCartOffer(CartItem cartItem){
    cartRepository.save(cartItem);
  }


  @Override public Collection<CartOffer> getAllCartOffers(User username){
    return cartRepository.findAllByUsername(username);
  }

  @Override public void deleteAllCartOffers(User username)
  {
    cartRepository.deleteAllByUsername(username);
  }
}
