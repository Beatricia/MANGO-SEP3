package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.FarmDaoInterface;
import mango.sep3.databaseaccess.FileData.FileContext;
import mango.sep3.databaseaccess.protobuf.Farm;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class FarmDAO implements FarmDaoInterface
{

  private FileContext context;
  private SessionFactory sessionFactory;


  //Since we are not using a DB yet we don't have to use this class yet
  /**
   *
   * @param context
   */
  public FarmDAO(FileContext context)
  {
    this.context = context;
  }
  @Override public void CreateFarm(Farm farm)
  {
    context.Farms().add(farm);

    mango.sep3.databaseaccess.Shared.Farm farm2 =new mango.sep3.databaseaccess.Shared.Farm(farm.getName());


    StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();

    try {
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }catch (Exception e){
      StandardServiceRegistryBuilder.destroy(registry);
    }

    try(Session session = sessionFactory.openSession()){
      session.beginTransaction();

      session.persist(farm2);

      session.getTransaction().commit();
    }
  }
}
