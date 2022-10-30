package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOImplementations.FarmDAO;
import mango.sep3.databaseaccess.FileData.DataContainer;
import mango.sep3.databaseaccess.FileData.FileContext;
import mango.sep3.databaseaccess.protobuf.Farm;
import mango.sep3.databaseaccess.protobuf.FarmServiceGrpc;

public class FarmServiceImpl extends FarmServiceGrpc.FarmServiceImplBase
{

  private FileContext fileContext;

  public FarmServiceImpl(FileContext fileContext)
  {
    this.fileContext = fileContext;
  }

  /**
   * The method creates a grpc Farm object using the parameters send in the
   * request. Then directly calls the FileContext instance to write the object to
   * the file and save the changes (this will be changed when the DB is implemented).
   * Finally returns the created object.
   * @param request  the Farm object send from the Logic Tier
   * @param responseObserver the object returned to the Logic Tier
   */
  @Override public void createFarm(Farm request,
      StreamObserver<Farm> responseObserver)
  {
    Farm farm = Farm.newBuilder()
        .setName(request.getName())
        .setPhone(request.getPhone())
        .setAddress(request.getAddress())
        .setCity(request.getCity())
        .setZip(request.getZip())
        .setFarmStatus(request.getFarmStatus())
        .setDeliveryDistance(request.getDeliveryDistance())
        .build();

        fileContext.Farms().add(farm);
        fileContext.SaveChanges();

    responseObserver.onNext(farm);
    responseObserver.onCompleted();
  }
}
