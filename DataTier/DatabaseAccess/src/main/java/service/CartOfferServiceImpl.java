package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOInterfaces.CartOfferInterface;
import mango.sep3.databaseaccess.DAOInterfaces.OfferDaoInterface;
import mango.sep3.databaseaccess.DAOInterfaces.UserDaoInterface;
import mango.sep3.databaseaccess.Shared.CartItem;
import mango.sep3.databaseaccess.Shared.Offer;
import mango.sep3.databaseaccess.Shared.User;
import mango.sep3.databaseaccess.protobuf.*;
import mango.sep3.databaseaccess.protobuf.Void;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@GRpcService
public class CartOfferServiceImpl extends CartOfferServiceGrpc.CartOfferServiceImplBase
{
  @Autowired private CartOfferInterface cartOfferDAO;
  @Autowired private OfferDaoInterface offerDaoInterface;
  @Autowired private UserDaoInterface userDaoInterface;


  public CartOfferServiceImpl()
  {
  }

  @Override public void addToCart(CartOffer request,
      StreamObserver<mango.sep3.databaseaccess.protobuf.Void> responseObserver)
  {
    CartItem cartItem = new CartItem();
    cartItem.setCartItemId(request.getId());

    int offerId = request.getOfferId();
    Offer off = offerDaoInterface.getOfferById(offerId);

    cartItem.setOfferId(off);
    cartItem.setQuantity(request.getQuantity());

    String userName = request.getUsername();
    User user = userDaoInterface.getUserByUsername(userName);

    user.setUsername(userName);
    cartItem.setUsername(user);
    cartItem.setCollectionOption(request.getCollectionOption());

    cartOfferDAO.createCartOffer(cartItem);

    responseObserver.onNext(Void.newBuilder().build());
    responseObserver.onCompleted();
  }


  //not working
  @Override public void getAllCartOffers(Username request,
      StreamObserver<CartOffers> responseObserver) {
    User usr = new User();
    usr.setUsername(request.getUsername());

    Collection<CartItem >cartItems =  cartOfferDAO.getAllCartOffers(usr);
    Collection<CartOffer> cartOffersBuf = new ArrayList<>();

    //proto
    CartOffers.Builder response = CartOffers.newBuilder();

    if(cartItems == null){
      responseObserver.onError(new Exception("No Order Offers found"));
      return;
    }

    for (CartItem item : cartItems)
    {
      CartOffer co = CartOffer.newBuilder()
          .setId(item.getCartItemId())
          .setOfferId(item.getOfferId().getId())
          .setQuantity(item.getQuantity())
          .setCollectionOption(item.getCollectionOption())
          .setUsername(item.getUsername().getUsername())
          .build();
      cartOffersBuf.add(co);
    }
    response.addAllCartOffers(cartOffersBuf);

    responseObserver.onNext(response.build());
    responseObserver.onCompleted();
  }

  @Transactional
  @Override public void deleteAllCartOffers(Username request,
      StreamObserver<Void> responseObserver) {
    User user = new User();
    user.setUsername(request.getUsername());
    cartOfferDAO.deleteAllCartOffers(user);

    responseObserver.onNext(Void.newBuilder().build());
    responseObserver.onCompleted();
  }
}