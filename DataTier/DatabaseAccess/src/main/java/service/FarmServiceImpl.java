package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOImplementations.FarmDAO;
import mango.sep3.databaseaccess.DAOInterfaces.FarmDaoInterface;
import mango.sep3.databaseaccess.FileData.FileContext;
import mango.sep3.databaseaccess.Repositories.FarmRepository;
import mango.sep3.databaseaccess.Shared.Address;
import mango.sep3.databaseaccess.protobuf.Farm;
import mango.sep3.databaseaccess.protobuf.FarmServiceGrpc;
import mango.sep3.databaseaccess.protobuf.Text;
import mango.sep3.databaseaccess.protobuf.User;
import mango.sep3.databaseaccess.protobuf.Void;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@GRpcService
public class FarmServiceImpl extends FarmServiceGrpc.FarmServiceImplBase
{

  @Autowired
  private FarmDaoInterface farmDAO;


  public FarmServiceImpl()
  {
  }

  /**
   * The method creates a grpc Farm object using the parameters send in the
   * request. Then directly calls the FileContext instance to write the object to
   * the file and save the changes (this will be changed when the DB is implemented).
   * Finally, returns the created object.
   * @param request  the Farm object send from the Logic Tier
   * @param responseObserver the object returned to the Logic Tier
   */


  @Override public void createFarm(Farm request,
      StreamObserver<Void> responseObserver)
  {
    Address address = new Address(request.getCity(),request.getAddress(),request.getZip());

    mango.sep3.databaseaccess.Shared.Farm farm = new mango.sep3.databaseaccess.Shared.Farm(request.getName(),request.getPhone(),request.getFarmStatus(),
        request.getDeliveryDistance(),address);

    farmDAO.createFarm(farm);
    Void response = Void.newBuilder().build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override public void getFarm(Text request,
      StreamObserver<Farm> responseObserver)
  {
    mango.sep3.databaseaccess.Shared.Farm farm = farmDAO.getFarmByName(request.getText());

    if (farm == null)
    {
      responseObserver.onError(new Exception("User not found"));
      return;
    }

    Farm farmToSend = Farm.newBuilder().setName(farm.getName()).setPhone(farm.getPhone()).setZip(farm.getAddress().getZip())
        .setAddress(farm.getAddress().getStreet()).setCity(farm.getAddress().getCity()).setFarmStatus(farm.getDescription()).setDeliveryDistance(farm.getDeliveryDistance()).build();


    responseObserver.onNext(farmToSend);
    responseObserver.onCompleted();
  }

}
