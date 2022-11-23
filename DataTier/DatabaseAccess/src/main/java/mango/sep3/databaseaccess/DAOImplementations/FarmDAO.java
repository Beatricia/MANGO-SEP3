package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.FarmDaoInterface;
import mango.sep3.databaseaccess.FileData.FileContext;
import mango.sep3.databaseaccess.Repositories.FarmRepository;
import mango.sep3.databaseaccess.protobuf.Farm;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@Repository
public class FarmDAO implements FarmDaoInterface
{

  private FileContext context;
  private FarmRepository farmRepository;


  //Since we are not using a DB yet we don't have to use this class yet
  /**
   *
   * @param
   */
  @Autowired
  public FarmDAO(FarmRepository farmRepository)
  {
    this.farmRepository = farmRepository;
  }
  @Override public void CreateFarm(Farm farm)
  {
    mango.sep3.databaseaccess.Shared.Farm farm2 =new mango.sep3.databaseaccess.Shared.Farm(farm.getName());
    farm2.setIconPath(farm.getIconPath());

    farmRepository.save(farm2);

  }
}
