package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOImplementations.FarmDAO;
import mango.sep3.databaseaccess.FileData.FileContext;
import mango.sep3.databaseaccess.Repositories.FarmRepository;
import mango.sep3.databaseaccess.protobuf.Farm;
import mango.sep3.databaseaccess.protobuf.FarmServiceGrpc;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@GRpcService
public class FarmServiceImpl extends FarmServiceGrpc.FarmServiceImplBase
{

  @Autowired
  private FileContext fileContext;
  @Autowired
  private FarmDAO farmDAO;

  private FarmRepository farmRepository;

  public FarmServiceImpl()
  {
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
    var farm = convertToShared(request);

    farmDAO.CreateFarm(farm);

    responseObserver.onNext(request);
    responseObserver.onCompleted();
  }


  // convert grpc farm to shared in a method
  private mango.sep3.databaseaccess.Shared.Farm convertToShared(Farm request) {
    mango.sep3.databaseaccess.Shared.Farm farm = new mango.sep3.databaseaccess.Shared.Farm();
    farm.setName(request.getName());
    return farm;
  }

  // convert shared farm to grpc in a method
    private Farm convertToGrpc(mango.sep3.databaseaccess.Shared.Farm farm) {
      return Farm.newBuilder().setName(farm.getName()).build();
    }
}
