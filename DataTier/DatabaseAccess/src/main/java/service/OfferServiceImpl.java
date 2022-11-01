package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.FileData.FileContext;
import mango.sep3.databaseaccess.protobuf.Offer;
import mango.sep3.databaseaccess.protobuf.OfferItems;
import mango.sep3.databaseaccess.protobuf.OfferServiceGrpc;
import mango.sep3.databaseaccess.protobuf.Void;

import java.util.ArrayList;
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
    //Creating the proto OfferItems
    OfferItems.Builder response = OfferItems.newBuilder();
    //Creating the Offers from the model
    ArrayList<Offer> offers = (ArrayList<Offer>) fileContext.Offers();

    Collection<Offer> offersList = new ArrayList<>();

    for (int i = 0; i < offers.size(); i++)
    {
      Offer off = Offer.newBuilder()
      .setOfferId(offers.get(i).getOfferId())
      .setTitle(offers.get(i).getTitle())
      .setPrice(offers.get(i).getPrice())
      .setPhotoPath(offers.get(i).getPhotoPath())
          .build();

      offersList.add(off);
    }

    response.addAllOffers(offersList);

    //sending back data to the client
    responseObserver.onNext(response.build());
    responseObserver.onCompleted();
  }
}
