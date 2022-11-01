package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.FileData.FileContext;
import mango.sep3.databaseaccess.protobuf.Farm;
import mango.sep3.databaseaccess.protobuf.Offer;
import mango.sep3.databaseaccess.protobuf.OfferServiceGrpc;

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
}
