package mango.sep3.databaseaccess;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import mango.sep3.databaseaccess.DAOImplementations.FarmDAO;
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

  }

}
