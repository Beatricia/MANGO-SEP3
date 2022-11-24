import io.grpc.Server;
import io.grpc.ServerBuilder;
import mango.sep3.databaseaccess.DAOImplementations.FarmDAO;
import mango.sep3.databaseaccess.FileData.FileContext;
import mango.sep3.databaseaccess.protobuf.Farm;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.stereotype.Service;
import service.CartOfferServiceImpl;
import service.FarmServiceImpl;
import service.OfferServiceImpl;

import java.io.IOException;

@GRpcService
public class GrpcServer
{
  public static void main(String[] args)
      throws IOException, InterruptedException
  {

    Server server = ServerBuilder
        .forPort(8084)
        .addService(new FarmServiceImpl())
        .build();

   server.start();

    //keep the server running in the foreground blocking the prompt
    server.awaitTermination();
  }
}
