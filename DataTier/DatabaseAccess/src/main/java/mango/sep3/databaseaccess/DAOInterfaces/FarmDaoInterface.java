package mango.sep3.databaseaccess.DAOInterfaces;


import mango.sep3.databaseaccess.Shared.Farm;

public interface FarmDaoInterface
{
  void CreateFarm(Farm farm);
  Farm getFarmByName(String name);
}
