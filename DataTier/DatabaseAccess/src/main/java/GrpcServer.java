import io.grpc.Server;
import io.grpc.ServerBuilder;
import mango.sep3.databaseaccess.FileData.FileContext;
import service.FarmServiceImpl;
import service.OfferServiceImpl;

import java.io.IOException;

public class GrpcServer
{
  public static void main(String[] args)
      throws IOException, InterruptedException
  {
    FileContext context = new FileContext();

    Server server = ServerBuilder
        .forPort(8084)
        .addService(new FarmServiceImpl(context))
        .addService(new OfferServiceImpl(context))
        .build();

   server.start();

    System.out.println("Server started at: " + server.getPort());

    //keep the server running in the foreground blocking the prompt
    server.awaitTermination();
  }
}
