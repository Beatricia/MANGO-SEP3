package mango.sep3.databaseaccess.DAOInterfaces;

import mango.sep3.databaseaccess.Shared.Farm;
import mango.sep3.databaseaccess.Shared.Farmer;
import mango.sep3.databaseaccess.Shared.Order;

import java.util.Collection;

public interface FarmDaoInterface
{
  Farm createFarm(Farm farm);
  Farm getFarmByName(String farmName);
  Collection<Farm> getFarms(Farmer farmer);
  Farm updateFarm(String name, String status, String phone);
  Collection<Order> getUncompletedOrdersFromFarm(String farmName);

  Collection<Farm> getAllFarms();
  Collection<Farm> getAllFarmsByName(String nameContains);

  void disableFarmById(String farmName);
}
