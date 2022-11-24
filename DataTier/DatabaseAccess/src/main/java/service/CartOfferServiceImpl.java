package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOImplementations.CartOfferDAO;
import mango.sep3.databaseaccess.FileData.FileContext;
import mango.sep3.databaseaccess.protobuf.CartOffer;
import mango.sep3.databaseaccess.protobuf.CartOfferServiceGrpc;
import mango.sep3.databaseaccess.protobuf.Farm;
import org.springframework.beans.factory.annotation.Autowired;

public class CartOfferServiceImpl extends CartOfferServiceGrpc.CartOfferServiceImplBase
{
  @Autowired
  private CartOfferDAO fileContext;

  public CartOfferServiceImpl(FileContext fileContext){
    this.fileContext = fileContext;
  }

  @Override public void addToCart(CartOffer request,
      StreamObserver<mango.sep3.databaseaccess.protobuf.Void> responseObserver) {
    CartOffer cartOffer = CartOffer.newBuilder()
        .setId(request.getId())
        .setOffer(request.getOffer())
        .setQuantity(request.getQuantity())
        .setUsername(request.getUsername())
        .setCollectionOption(request.getCollectionOption())
        .build();

    fileContext.CartOffers().add(cartOffer);
    fileContext.SaveChanges();

    responseObserver.onCompleted();
  }
}
