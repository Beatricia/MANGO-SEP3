package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.CartOfferInterface;
import mango.sep3.databaseaccess.FileData.FileContext;
import mango.sep3.databaseaccess.Repositories.CartRepository;
import mango.sep3.databaseaccess.Shared.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
