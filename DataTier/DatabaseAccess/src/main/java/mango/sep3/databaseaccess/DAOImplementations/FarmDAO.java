package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.FarmDaoInterface;
import mango.sep3.databaseaccess.Repositories.FarmRepository;
import mango.sep3.databaseaccess.Repositories.FarmerRepository;
import mango.sep3.databaseaccess.Shared.Farm;
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
    this.farmRepository = farmRepository;
  }
  @Override public Farm createFarm(Farm farm)
  {
    var farmer = farmerRepository.findById(farm.getFarmer().getUsername());
    farm.setFarmer(farmer.get());
    return farmRepository.saveAndFlush(farm);
  }

  @Override public Farm getFarmByName(String farmName)
  {
    return farmRepository.findByName(farmName);
  }
}
