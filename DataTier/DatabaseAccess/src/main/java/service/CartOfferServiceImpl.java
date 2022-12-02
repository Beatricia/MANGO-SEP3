package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOInterfaces.CartOfferInterface;
import mango.sep3.databaseaccess.DAOInterfaces.OfferDaoInterface;
import mango.sep3.databaseaccess.DAOInterfaces.UserDaoInterface;
import mango.sep3.databaseaccess.Shared.CartItem;
import mango.sep3.databaseaccess.Shared.Customer;
import mango.sep3.databaseaccess.Shared.Offer;
import mango.sep3.databaseaccess.Shared.User;
import mango.sep3.databaseaccess.protobuf.*;
import mango.sep3.databaseaccess.protobuf.Void;
import mango.sep3.databaseaccess.utils.GrpcConverter;
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

  @Autowired private GrpcConverter grpcConverter;


  public CartOfferServiceImpl()
  {
  }

  @Override public void addToCart(CartOffer request,
      StreamObserver<mango.sep3.databaseaccess.protobuf.Void> responseObserver)
  {
    CartItem cartItem = new CartItem();
    cartItem.setCartItemId(request.getId());

    Offer off = offerDaoInterface.getOfferById(request.getOffer().getId());

    cartItem.setOfferId(off);
    cartItem.setQuantity(request.getQuantity());

    String userName = request.getUsername();
    Customer customer = userDaoInterface.getCustomer(userName);

    cartItem.setCustomer(customer);
    cartItem.setCollectionOption(request.getCollectionOption());

    cartOfferDAO.createCartOffer(cartItem);

    responseObserver.onNext(Void.newBuilder().build());
    responseObserver.onCompleted();
  }


  //not working
  @Override public void getAllCartOffers(Username request,
      StreamObserver<CartOffers> responseObserver) {
    Customer customer = userDaoInterface.getCustomer(request.getUsername());

    Collection<CartItem >cartItems =  cartOfferDAO.getAllCartOffers(customer);
    Collection<CartOffer> cartOffersBuf = new ArrayList<>();

    //proto
    CartOffers.Builder response = CartOffers.newBuilder();

    if(cartItems == null){
      responseObserver.onError(new Exception("No Order Offers found"));
      return;
    }

    for (CartItem item : cartItems)
    {
      CartOffer co = grpcConverter.convertToGrpc(item);
      cartOffersBuf.add(co);
    }
    response.addAllCartOffers(cartOffersBuf);

    responseObserver.onNext(response.build());
    responseObserver.onCompleted();
  }

  @Transactional
  @Override public void deleteAllCartOffers(Username request,
      StreamObserver<Void> responseObserver) {
    Customer customer = userDaoInterface.getCustomer(request.getUsername());

    cartOfferDAO.deleteAllCartOffers(customer);

    responseObserver.onNext(Void.newBuilder().build());
    responseObserver.onCompleted();
  }

   public void getById(Id request,
      StreamObserver<CartOffer> responseObserver) {
      CartItem cartItem = cartOfferDAO.getById(request.getId());

      CartOffer co = grpcConverter.convertToGrpc(cartItem);

    responseObserver.onNext(co);
    responseObserver.onCompleted();
  }

  /**
   */
 public void deleteCartOffer(Id request,
      StreamObserver<Void> responseObserver) {
   cartOfferDAO.deleteCartOffer(request.getId());

    responseObserver.onNext(Void.newBuilder().build());
    responseObserver.onCompleted();
  }
}