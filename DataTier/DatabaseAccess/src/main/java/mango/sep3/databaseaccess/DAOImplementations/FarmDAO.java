package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.FarmDaoInterface;
import mango.sep3.databaseaccess.FileData.FileContext;
import mango.sep3.databaseaccess.protobuf.Farm;

public class FarmDAO implements FarmDaoInterface
{

  private FileContext context;


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
  }
}
