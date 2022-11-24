package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOImplementations.CartOfferDAO;
import mango.sep3.databaseaccess.Shared.CartItem;
import mango.sep3.databaseaccess.Shared.User;
import mango.sep3.databaseaccess.protobuf.*;
import mango.sep3.databaseaccess.protobuf.Void;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class CartOfferServiceImpl extends CartOfferServiceGrpc.CartOfferServiceImplBase
{
  @Autowired private CartOfferDAO cartOfferDAO;

  public CartOfferServiceImpl()
  {
  }

  @Override public void addToCart(CartOffer request,
      StreamObserver<mango.sep3.databaseaccess.protobuf.Void> responseObserver)
  {
    CartItem cartItem = new CartItem();
    cartItem.setCartItemId(request.getId());
    //int offerId = request.getOfferId();
    //Offer off = new Offer();
    //off.
    //cartItem.setOfferId((Offer) request.getOfferId());
    cartItem.setQuantity(request.getQuantity());
    String userName = request.getUsername();
    User user = new User();
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
    User user = new User();
    user.setUsername(request.getUsername());
    Iterable<? extends CartOffer> cartOffersModel = cartOfferDAO.getAllCartOffers(user);

    CartOffers cartOffers = CartOffers.newBuilder()
        .addAllCartOffers(cartOffersModel)
        .build();

    responseObserver.onNext(cartOffers);
    responseObserver.onCompleted();
  }

  //not working
  @Override public void deleteAllCartOffers(Username request,
      StreamObserver<Void> responseObserver) {
    User user = new User();
    user.setUsername(request.getUsername());
    cartOfferDAO.deleteAllCartOffers(user);

    responseObserver.onNext(Void.newBuilder().build());
    responseObserver.onCompleted();
  }
}