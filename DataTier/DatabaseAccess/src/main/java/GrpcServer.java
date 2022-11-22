import io.grpc.Server;
import io.grpc.ServerBuilder;
import mango.sep3.databaseaccess.DAOImplementations.FarmDAO;
import mango.sep3.databaseaccess.FileData.FileContext;
import mango.sep3.databaseaccess.protobuf.Farm;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.stereotype.Service;
import service.FarmServiceImpl;
import service.OfferServiceImpl;

import java.io.IOException;

@GRpcService
public class GrpcServer
{
  public static void main(String[] args)
      throws IOException, InterruptedException
  {
    FileContext context = new FileContext();
    FarmDAO farmDAO = new FarmDAO(context);

    Server server = ServerBuilder
        .forPort(8084)
        .addService(new FarmServiceImpl(context, farmDAO))
        .addService(new OfferServiceImpl(context)).build();

   server.start();

    //keep the server running in the foreground blocking the prompt
    server.awaitTermination();
  }
}
