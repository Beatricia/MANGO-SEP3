package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOInterfaces.FarmDaoInterface;
import mango.sep3.databaseaccess.DAOInterfaces.UserDaoInterface;
import mango.sep3.databaseaccess.Shared.Address;
import mango.sep3.databaseaccess.protobuf.*;

import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

@GRpcService public class FarmServiceImpl
    extends FarmServiceGrpc.FarmServiceImplBase
{
  @Autowired private FarmDaoInterface farmDAO;
  @Autowired private UserDaoInterface userDao;

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
      StreamObserver<Farm> responseObserver)
  {
    mango.sep3.databaseaccess.Shared.Farm farm = new mango.sep3.databaseaccess.Shared.Farm(
        request.getName(), request.getPhone(), request.getFarmStatus(),
        request.getDeliveryDistance(),
        convertAddressFromGrpc(request.getAddress()),
        convertFarmerFromGrpc(request.getFarmer()));

    farm.setIconPath(request.getIconPath());

    // get the farmer from the database by username
    mango.sep3.databaseaccess.Shared.Farmer farmer = userDao.getFarmer(
        request.getFarmer().getUsername());
    farm.setFarmer(farmer);

    farmDAO.createFarm(farm);
    Farm farmGrpc = convertFarmToGrpc(farm);

    responseObserver.onNext(farmGrpc);
    responseObserver.onCompleted();
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

    Farm farmToSend = convertFarmToGrpc(farm);

    responseObserver.onNext(farmToSend);
    responseObserver.onCompleted();
  }

  @Override public void getFarms(
      mango.sep3.databaseaccess.protobuf.Farmer request,
      io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Farms> responseObserver)
  {

    mango.sep3.databaseaccess.Shared.Farmer farmer = userDao.getFarmer(
        request.getUsername());
    if (farmer == null)
    {
      responseObserver.onError(new Exception("This farmer does not exist"));
    }

    assert farmer != null;
    farmer.setUsername(request.getUsername());

    //the response to send back
    Farms.Builder response = Farms.newBuilder();

    //model collection
    Collection<mango.sep3.databaseaccess.Shared.Farm> farms = farmDAO.getFarms(
        farmer);
    //protobuff collection
    Collection<Farm> protoFarms = new ArrayList<>();

    if (farms == null)
    {
      responseObserver.onError(new Exception("No Farms found"));
      return;
    }

    for (mango.sep3.databaseaccess.Shared.Farm farm : farms)
    {
      Farm f = Farm.newBuilder().setName(farm.getName())
          .setAddress(convertAddressToGrpc(farm.getAddress()))
          .setFarmer(convertFarmerToGrpc(farm.getFarmer()))
          .setFarmStatus(farm.getDescription())
          .setDeliveryDistance(farm.getDeliveryDistance())
          .setPhone(farm.getPhone()).build();
      protoFarms.add(f);
    }
    response.addAllFarms(protoFarms);

    responseObserver.onNext(response.build());
    responseObserver.onCompleted();

  }

  @Override public void getFarmByName(Text request,
      StreamObserver<Farm> responseObserver)
  {
    var farm = farmDAO.getFarmByName(request.getText());
    var grpcFarm = convertFarmToGrpc(farm);

    responseObserver.onNext(grpcFarm);
    responseObserver.onCompleted();
  }

  @Override public void updateFarm(FarmUpdate request,
      StreamObserver<Farm> responseObserver)
  {
    var updatedFarm = farmDAO.updateFarm(request.getName(), request.getStatus());

    responseObserver.onNext(convertFarmToGrpc(updatedFarm));
    responseObserver.onCompleted();
  }

  private Farmer convertFarmerToGrpc(
      mango.sep3.databaseaccess.Shared.Farmer farmer)
  {

    return Farmer.newBuilder().setFirstname(farmer.getFirstName())
        .setUsername(farmer.getUsername()).setLastname(farmer.getLastName())
        .build();
  }



  private mango.sep3.databaseaccess.protobuf.Address convertAddressToGrpc(
      Address address)
  {

    return mango.sep3.databaseaccess.protobuf.Address.newBuilder()
        .setCity(address.getCity()).setZip(address.getZip())
        .setStreet(address.getCity()).build();
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

  // convert shared farm to grpc in a method
  private Farm convertFarmToGrpc(mango.sep3.databaseaccess.Shared.Farm farm)
  {
    return Farm.newBuilder().setName(farm.getName())
        .setFarmer(convertFarmerToGrpc(farm.getFarmer()))
        .setAddress(convertAddressToGrpc(farm.getAddress()))
        .setFarmStatus(farm.getDescription())
        .setDeliveryDistance(farm.getDeliveryDistance())
        .setPhone(farm.getPhone()).setIconPath(farm.getIconPath()).build();
  }

  private mango.sep3.databaseaccess.Shared.Farmer convertFarmerFromGrpc(
      Farmer farmerGrpc)
  {
    var farmer = new mango.sep3.databaseaccess.Shared.Farmer();
    farmer.setFirstName(farmerGrpc.getFirstname());
    farmer.setLastName(farmerGrpc.getLastname());
    farmer.setUsername(farmerGrpc.getUsername());

    return farmer;
  }
}
