package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOInterfaces.FarmDaoInterface;
import mango.sep3.databaseaccess.DAOInterfaces.OrderDaoInterface;
import mango.sep3.databaseaccess.DAOInterfaces.UserDaoInterface;
import mango.sep3.databaseaccess.Shared.Address;
import mango.sep3.databaseaccess.protobuf.*;

import mango.sep3.databaseaccess.utils.GrpcConverter;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@GRpcService public class FarmServiceImpl
    extends FarmServiceGrpc.FarmServiceImplBase
{
  @Autowired private FarmDaoInterface farmDAO;
  @Autowired private UserDaoInterface userDao;
  @Autowired private OrderDaoInterface orderDao;

  @Autowired private GrpcConverter grpcConverter;

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
    mango.sep3.databaseaccess.Shared.Farm farm = grpcConverter.convertToShared(request);

    farm.setIconPath(request.getIconPath());

    // get the farmer from the database by username
    mango.sep3.databaseaccess.Shared.Farmer farmer = userDao.getFarmer(
        request.getFarmer().getUsername());
    farm.setFarmer(farmer);

    farmDAO.createFarm(farm);
    Farm farmGrpc = grpcConverter.convertToGrpc(farm);

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

    Farm farmToSend = grpcConverter.convertToGrpc(farm);

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
      Farm f = grpcConverter.convertToGrpc(farm);
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
    var grpcFarm = grpcConverter.convertToGrpc(farm);

    responseObserver.onNext(grpcFarm);
    responseObserver.onCompleted();
  }

  @Override public void updateFarm(FarmUpdate request,
      StreamObserver<Farm> responseObserver)
  {
    mango.sep3.databaseaccess.Shared.Farm updatedFarm = farmDAO.updateFarm(request.getName(), request.getStatus());

    responseObserver.onNext(grpcConverter.convertToGrpc(updatedFarm));
    responseObserver.onCompleted();
  }

  @Override public void getAllCustomersUncompletedOrder(Text request,
      StreamObserver<RepeatedUsername> responseObserver)
  {
    //get the ids of all uncompleted orders from the specific farm
    var orderIds = farmDAO.getUncompletedOrdersFromFarm(request.getText());

    //get all the usersNames from those orders
    var usernames = orderDao.getUsersWithUncompletedOrder(orderIds);

    responseObserver.onNext(grpcConverter.convertToGrpcRU(usernames));
    responseObserver.onCompleted();
  }
}
