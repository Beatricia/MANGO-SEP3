package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOImplementations.CartOfferDAO;
import mango.sep3.databaseaccess.FileData.FileContext;
import mango.sep3.databaseaccess.Shared.CartItem;
import mango.sep3.databaseaccess.protobuf.CartOffer;
import mango.sep3.databaseaccess.protobuf.CartOfferServiceGrpc;
import mango.sep3.databaseaccess.protobuf.Farm;
import mango.sep3.databaseaccess.protobuf.Void;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

@GRpcService
public class CartOfferServiceImpl extends CartOfferServiceGrpc.CartOfferServiceImplBase
{

  @Autowired
  private FileContext fileContext;
  @Autowired
  private CartOfferDAO cartOfferDAO;

  public CartOfferServiceImpl(){
  }

  @Override public void addToCart(CartOffer request,
      StreamObserver<mango.sep3.databaseaccess.protobuf.Void> responseObserver) {
    CartItem cartItem = new CartItem();
    cartItem.setCartItemId(request.getId());
    cartItem.setCartItemId(request.getOfferId());
    cartItem.setUsername(request.getUsername());
    cartItem.setCollectionOption(request.getCollectionOption());

    cartOfferDAO.createCartOffer(cartItem);

    responseObserver.onNext(Void.newBuilder().build());
    responseObserver.onCompleted();
  }
}
