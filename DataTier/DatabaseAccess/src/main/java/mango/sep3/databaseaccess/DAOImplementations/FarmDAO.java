package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.FarmDaoInterface;
import mango.sep3.databaseaccess.FileData.FileContext;
import mango.sep3.databaseaccess.Repositories.FarmRepository;
import mango.sep3.databaseaccess.Shared.Farm;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

/**
 * Data access object accessing farm related data.
 */
@Repository
public class FarmDAO implements FarmDaoInterface
{

  private FileContext context;
  private FarmRepository farmRepository;

  /**
   *
   * @param
   */
  @Autowired
  public FarmDAO(FarmRepository farmRepository)
  {
    this.farmRepository = farmRepository;
  }
  @Override public void createFarm(Farm farm)
  {
    farmRepository.save(farm);
  }

  @Override public Farm getFarmByName(String farmName)
  {
    Farm farm = farmRepository.findByName(farmName);
    return farm;
  }
}
