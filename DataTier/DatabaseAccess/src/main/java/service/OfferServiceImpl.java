package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOInterfaces.OfferDaoInterface;
import mango.sep3.databaseaccess.protobuf.*;
import mango.sep3.databaseaccess.protobuf.Void;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A class responsable for taking the data from the database (currently from
 * a textFile) and sending it to the Logic Tier
 */
@GRpcService
public class OfferServiceImpl extends OfferServiceGrpc.OfferServiceImplBase
{
  @Autowired
  private OfferDaoInterface offerDao;

  public OfferServiceImpl()
  {
  }

  /**
   * The method creates a grpc Offer object using the parameters send in the
   * request. Then directly calls the FileContext instance to write the object to
   * the file and save the changes (this will be changed when the DB is implemented).
   * Finally, returns the created object.
   * @param request  the Offer object send from the Logic Tier
   * @param responseObserver the object returned to the Logic Tier
   */
  @Override public void createOffer(OfferCreation request,
      StreamObserver<Offer> responseObserver)
  {

    mango.sep3.databaseaccess.Shared.Offer offer = new mango.sep3.databaseaccess.Shared.Offer();

      offer.setName(request.getName());
      offer.setQuantity(request.getQuantity());
      offer.setUnit(request.getUnit());
      offer.setPrice(request.getPrice());
      offer.setDelivery(request.getDelivery());
      offer.setPickUp(request.getPickUp());
      offer.setPickyourOwn(request.getPickYourOwn());
      offer.setDescription(request.getDescription());
      offer.setImgPath(request.getImagePath());


    offerDao.CreateOffer(offer);

    Offer response =  convertOfferToGrpc(offer);

    responseObserver.onNext(response);
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
    Collection<mango.sep3.databaseaccess.Shared.Offer> offers = offerDao.GetOffer();

    Collection<Offer> offersList = new ArrayList<>();

    for (var offer: offers)
    {
      Offer offerToSend = Offer.newBuilder().setId(offer.getId()).setName(offer.getName()).setQuantity(offer.getQuantity())
          .setUnit(offer.getUnit()).setPrice(offer.getPrice()).setDelivery(offer.isDelivery()).setPickUp(offer.isPickUp())
          .setPickYourOwn(offer.isPickyourOwn()).setDescription(offer.getDescription()).setImagePath(offer.getImgPath()).build();
      offersList.add(offerToSend);
    }

    OfferItems response = OfferItems.newBuilder().addAllOffers(offersList).build();
    //sending back data to the client
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override public void getOfferById(Id request,
      StreamObserver<Offer> responseObserver)
  {
    mango.sep3.databaseaccess.Shared.Offer offer = offerDao.getOfferById(request.getId());

    Offer response = convertOfferToGrpc(offer);

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  private Offer convertOfferToGrpc(
      mango.sep3.databaseaccess.Shared.Offer offer)
  {
    Offer response = Offer.newBuilder().setId(offer.getId()).setName(offer.getName()).setQuantity(
            offer.getQuantity()).setUnit(offer.getUnit())
        .setPrice(offer.getPrice()).setDelivery(offer.isDelivery()).setPickUp(offer.isPickUp())
        .setPickYourOwn(offer.isPickyourOwn()).setDescription(offer.getDescription()).setImagePath(offer.getImgPath()).build();


    return response;
  }
}
