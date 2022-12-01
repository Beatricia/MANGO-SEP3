package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.FarmDaoInterface;
import mango.sep3.databaseaccess.Repositories.FarmRepository;
import mango.sep3.databaseaccess.Repositories.FarmerRepository;
import mango.sep3.databaseaccess.Shared.Farm;
import mango.sep3.databaseaccess.Shared.Farmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

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

 @Override public Collection<Farm> getFarms(Farmer farmer)
  {
    System.out.println(farmRepository.findAllByFarmer(farmer));
    return farmRepository.findAllByFarmer(farmer);
  }

  @Override public Farm updateFarm(String name, String status)
  {
    Farm farm = farmRepository.findByName(name);

    if (!status.isEmpty())
    {
      farm.setDescription(status);
      farmRepository.saveAndFlush(farm);
    }
    return farm;
  }
}
