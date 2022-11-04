package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.FileData.FileContext;
import mango.sep3.databaseaccess.protobuf.*;
import mango.sep3.databaseaccess.protobuf.Void;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A class responsable for taking the data from the database (currently from
 * a textFile) and sending it to the Logic Tier
 */
public class OfferServiceImpl extends OfferServiceGrpc.OfferServiceImplBase
{
  private FileContext context;

  public OfferServiceImpl(FileContext context)
  {
    this.context = context;
  }

  /**
   * The method creates a grpc Offer object using the parameters send in the
   * request. Then directly calls the FileContext instance to write the object to
   * the file and save the changes (this will be changed when the DB is implemented).
   * Finally returns the created object.
   * @param request  the Offer object send from the Logic Tier
   * @param responseObserver the object returned to the Logic Tier
   */
  @Override public void createOffer(Offer request, StreamObserver<Offer> responseObserver)
  {
    Offer offer = Offer.newBuilder()
        .setId(request.getId())
        .setName(request.getName())
        .setQuantity(request.getQuantity())
        .setUnit(request.getUnit())
        .setPrice(request.getPrice())
        .setDelivery(request.getDelivery())
        .setPickUp(request.getPickUp())
        .setPickYourOwn(request.getPickYourOwn())
        .setDescription(request.getDescription())
        .setImagePath(request.getImagePath())
        .build();

    context.Offers().add(offer);
    context.SaveChanges();

    responseObserver.onNext(offer);
    responseObserver.onCompleted();
  }

  /**
   * Getting the offers from the database(currently from the file context)
   * @param request is a Void object that contains null
   * @param responseObserver the object returned to the Logic Tier
   */
  @Override public void getOffers(Void request,
      StreamObserver<OfferItems> responseObserver)
  {
    //Creating the proto OfferItems
    OfferItems.Builder response = OfferItems.newBuilder();
    //Creating the Offers from the model
    ArrayList<Offer> offers = (ArrayList<Offer>) context.Offers();

    Collection<Offer> offersList = new ArrayList<>();

    for (int i = 0; i < offers.size(); i++)
    {
      Offer off = Offer.newBuilder()
          .setId(offers.get(i).getId())
          .setName(offers.get(i).getName())
          .setQuantity(offers.get(i).getQuantity())
          .setUnit(offers.get(i).getUnit())
          .setPrice(offers.get(i).getPrice())
          .setDelivery(offers.get(i).getDelivery())
          .setPickUp(offers.get(i).getPickUp())
          .setPickYourOwn(offers.get(i).getPickYourOwn())
          .setDescription(offers.get(i).getDescription())
          .setImagePath(offers.get(i).getImagePath())
          .build();

      offersList.add(off);
    }

    response.addAllOffers(offersList);
    //sending back data to the client
    responseObserver.onNext(response.build());
    responseObserver.onCompleted();
  }
}
