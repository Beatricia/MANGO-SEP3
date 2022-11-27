package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOInterfaces.FarmDaoInterface;
import mango.sep3.databaseaccess.DAOInterfaces.OfferDaoInterface;
import mango.sep3.databaseaccess.DAOInterfaces.UserDaoInterface;
import mango.sep3.databaseaccess.FileData.FileContext;
import mango.sep3.databaseaccess.Repositories.OfferRepository;
import mango.sep3.databaseaccess.protobuf.*;
import mango.sep3.databaseaccess.protobuf.Void;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A class responsable for taking the data from the database (currently from
 * a textFile) and sending it to the Logic Tier
 */
@GRpcService
public class OfferServiceImpl extends OfferServiceGrpc.OfferServiceImplBase
{
  @Autowired
  private OfferDaoInterface offerDaoInterface;

  @Autowired
  private FarmDaoInterface farmDaoInterface;

  public OfferServiceImpl()
  {
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
    mango.sep3.databaseaccess.Shared.Offer offer = convertToShared(request);

    var savedOffer = offerDaoInterface.CreateOffer(offer);

    responseObserver.onNext(convertToGrpc(savedOffer));
    responseObserver.onCompleted();
  }

  private mango.sep3.databaseaccess.Shared.Offer convertToShared(Offer request) {
    mango.sep3.databaseaccess.Shared.Offer offer = new mango.sep3.databaseaccess.Shared.Offer();
    offer.setName(request.getName());
    offer.setDescription(request.getDescription());
    offer.setImage(request.getImagePath());
    offer.setDelivery(request.getDelivery());
    offer.setPickUp(request.getPickUp());
    offer.setPickYourOwn(request.getPickYourOwn());
    offer.setPrice(request.getPrice());
    offer.setUnit(request.getUnit());

    var farm = farmDaoInterface.getFarmByName(request.getFarmName());
    offer.setFarm(farm);

    return offer;
  }

  // convert from shared to grpc offer
  private Offer convertToGrpc(mango.sep3.databaseaccess.Shared.Offer offer) {
    Offer offerResponse = Offer.newBuilder()
        .setId(offer.getId())
        .setName(offer.getName())
        .setDescription(offer.getDescription())
        .setImagePath(offer.getImage())
        .setDelivery(offer.isDelivery())
        .setPickUp(offer.isPickUp())
        .setPickYourOwn(offer.isPickYourOwn())
        .setPrice(offer.getPrice())
        .setUnit(offer.getUnit())
        .setFarmName(offer.getFarm().getName())
        .build();

    return offerResponse;
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
    Collection<mango.sep3.databaseaccess.Shared.Offer> offers =  offerDaoInterface.GetOffer();

    Collection<Offer> offersList = new ArrayList<>();


    for (mango.sep3.databaseaccess.Shared.Offer offer : offers)
    {
      var protoOffer = convertToGrpc(offer);
      response.addOffers(protoOffer);
    }


    response.addAllOffers(offersList);
    //sending back data to the client
    responseObserver.onNext(response.build());
    responseObserver.onCompleted();
  }
}
