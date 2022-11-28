package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOImplementations.FarmDAO;
import mango.sep3.databaseaccess.DAOInterfaces.FarmDaoInterface;
import mango.sep3.databaseaccess.FileData.FileContext;
import mango.sep3.databaseaccess.Repositories.FarmRepository;
import mango.sep3.databaseaccess.Shared.Address;
import mango.sep3.databaseaccess.Shared.Offer;
import mango.sep3.databaseaccess.protobuf.*;
import mango.sep3.databaseaccess.protobuf.Void;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@GRpcService public class FarmServiceImpl
    extends FarmServiceGrpc.FarmServiceImplBase
{
  @Autowired private FarmDaoInterface farmDAO;

  public FarmServiceImpl()
  {
  }

  /**
   * The method creates a grpc Farm object using the parameters send in the
   * request. Then directly calls the FileContext instance to write the object to
   * the file and save the changes (this will be changed when the DB is implemented).
   * Finally, returns the created object.
   *
   * @param request          the Farm object send from the Logic Tier
   * @param responseObserver the object returned to the Logic Tier
   */

  @Override public void createFarm(Farm request,
      StreamObserver<Void> responseObserver)
  {
    mango.sep3.databaseaccess.Shared.Farm farm = new mango.sep3.databaseaccess.Shared.Farm(
        request.getName(), request.getPhone(), request.getFarmStatus(),
        request.getDeliveryDistance(),
        convertAddressFromGrpc(request.getAddress()),
        convertFarmerFromGrpc(request.getFarmer()));

    farmDAO.createFarm(farm);
    Void response = Void.newBuilder().build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  private mango.sep3.databaseaccess.Shared.Farmer convertFarmerFromGrpc(Farmer farmerGrpc)
  {
    var farmer = new mango.sep3.databaseaccess.Shared.Farmer();
    farmer.setFirstName(farmerGrpc.getFirstname());
    farmer.setLastName(farmerGrpc.getLastname());
    farmer.setUsername(farmerGrpc.getUsername());

    return farmer;
  }

  @Override public void getFarm(Text request,
      StreamObserver<Farm> responseObserver)
  {
    mango.sep3.databaseaccess.Shared.Farm farm = farmDAO.getFarmByName(
        request.getText());

    if (farm == null)
    {
      responseObserver.onError(new Exception("User not found"));
      return;
    }

    Farm farmToSend = Farm.newBuilder().setName(farm.getName())
        .setPhone(farm.getPhone()).setFarmStatus(farm.getDescription())
        .setDeliveryDistance(farm.getDeliveryDistance())
        .setAddress(convertAddressToGrpc(farm.getAddress()))
        //.setFarmer(convertFarmerToGrpc(farm.getFarmer())))
        .build();

    responseObserver.onNext(farmToSend);
    responseObserver.onCompleted();
  }

  private Farmer convertFarmerToGrpc(
      mango.sep3.databaseaccess.Shared.Farmer farmer)
  {

    return Farmer.newBuilder()
        .setFirstname(farmer.getFirstName())
        .setUsername(farmer.getUsername())
        .setLastname(farmer.getLastName())
        .build();
  }

  private mango.sep3.databaseaccess.protobuf.Address convertAddressToGrpc(
      Address address)
  {

    return mango.sep3.databaseaccess.protobuf.Address.newBuilder()
        .setCity(address.getCity())
        .setZip(address.getZip())
        .setStreet(address.getCity())
        .build();
  }

  private Address convertAddressFromGrpc(
      mango.sep3.databaseaccess.protobuf.Address address)
  {
    Address address1 = new Address();
    address1.setCity(address.getCity());
    address1.setZip(address.getZip());
    address1.setStreet(address.getStreet());

    return address1;
  }


}
