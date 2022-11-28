package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.FarmDaoInterface;
import mango.sep3.databaseaccess.FileData.FileContext;
import mango.sep3.databaseaccess.Repositories.FarmRepository;
import mango.sep3.databaseaccess.Repositories.FarmerRepository;
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
  @Autowired
  private FarmRepository farmRepository;
  @Autowired
  private FarmerRepository farmerRepository;

  /**
   *
   * @param
   */
  @Autowired
  public FarmDAO()
  {
  }
  @Override public Farm createFarm(Farm farm)
  {
    System.out.println("Username: " + farm.getFarmer().getUsername());
    var farmer = farmerRepository.findById(farm.getFarmer().getUsername());
    farm.setFarmer(farmer.get());
    System.out.println("Farmer: " + farmer);
    return farmRepository.saveAndFlush(farm);
  }

  @Override public Farm getFarmByName(String farmName)
  {
    Farm farm = farmRepository.findByName(farmName);
    return farm;
  }
}
