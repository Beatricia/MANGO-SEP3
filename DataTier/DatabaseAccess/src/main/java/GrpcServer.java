import io.grpc.Server;
import io.grpc.ServerBuilder;
import mango.sep3.databaseaccess.DAOImplementations.FarmDAO;
import mango.sep3.databaseaccess.FileData.FileContext;
import service.FarmServiceImpl;

import java.io.IOException;

public class GrpcServer
{
  public static void main(String[] args)
      throws IOException, InterruptedException
  {
    FileContext context = new FileContext();

    Server server = ServerBuilder
        .forPort(8084)
        .addService(new FarmServiceImpl(context)).build();

   server.start();

    //keep the server running in the foreground blocking the prompt
    server.awaitTermination();
  }
}
