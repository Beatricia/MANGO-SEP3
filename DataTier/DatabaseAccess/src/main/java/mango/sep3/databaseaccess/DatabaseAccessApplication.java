package mango.sep3.databaseaccess;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import mango.sep3.databaseaccess.DAOImplementations.FarmDAO;
import mango.sep3.databaseaccess.FileData.FileContext;
import mango.sep3.databaseaccess.Repositories.FarmRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.FarmServiceImpl;
import service.OfferServiceImpl;

import java.io.IOException;
import java.util.EnumSet;

@SpringBootApplication public class DatabaseAccessApplication
{
  public static void main(String[] args)
  {
    SpringApplication.run(DatabaseAccessApplication.class, args);

    //GRPC SERVER
    try{
      FileContext context = new FileContext();
      FarmDAO farmDAO = new FarmDAO(context);

      Server server = ServerBuilder
              .forPort(8084)
              .addService(new FarmServiceImpl(context, farmDAO))
              .addService(new OfferServiceImpl(context)).build();

      server.start();

      //keep the server running in the foreground blocking the prompt
      server.awaitTermination();
    }catch (IOException | InterruptedException e){
      e.printStackTrace();
    }

  }

}
