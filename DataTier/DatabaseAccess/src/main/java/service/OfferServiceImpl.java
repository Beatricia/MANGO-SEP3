package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.FileData.FileContext;
import mango.sep3.databaseaccess.protobuf.Offer;
import mango.sep3.databaseaccess.protobuf.OfferItems;
import mango.sep3.databaseaccess.protobuf.OfferServiceGrpc;
import mango.sep3.databaseaccess.protobuf.Void;

import java.util.Collection;

public class OfferServiceImpl extends OfferServiceGrpc.OfferServiceImplBase
{
  private final FileContext fileContext;

  public OfferServiceImpl(FileContext context){
    fileContext = context;
  }
  @Override public void getOffers(Void request,
      StreamObserver<OfferItems> responseObserver)
  {
    OfferItems.Builder response = OfferItems.newBuilder();
    Collection<Offer> offers = fileContext.Offers();

    for (int i = 0; i < offers.size(); i++)
    {
      response.setOffers(offers.iterator().next().getOfferId(), offers.iterator().next());
    }

    //sending back data to the client
    responseObserver.onNext(response.build());
    responseObserver.onCompleted();
  }
}
