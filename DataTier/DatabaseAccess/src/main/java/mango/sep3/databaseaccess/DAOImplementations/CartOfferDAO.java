package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.CartOfferInterface;
import mango.sep3.databaseaccess.Repositories.CartRepository;
import mango.sep3.databaseaccess.Shared.CartItem;
import mango.sep3.databaseaccess.Shared.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class CartOfferDAO implements CartOfferInterface
{
  private CartRepository cartRepository;

  @Autowired
  public CartOfferDAO(CartRepository cartRepository){
    this.cartRepository = cartRepository;
  }

  @Override public void createCartOffer(CartItem cartItem){
    cartItem = cartRepository.saveAndFlush(cartItem);
  }

  @Override public Collection<CartItem> getAllCartOffers(Customer customer){
    return cartRepository.findAllByCustomer(customer);
  }

  @Override public void deleteAllCartOffers(Customer customer)
  {
    cartRepository.deleteAllByCustomer(customer);
  }


  @Override public CartItem getById(int id)
  {
    Optional<CartItem> cartItem = cartRepository.findById(id);

    //if it exists it returns it, if not, it returns null
    return cartItem.orElse(null);
  }

  @Override public void deleteCartOffer(int id)
  {
    cartRepository.deleteById(id);
  }
}
