package mango.sep3.databaseaccess.FileData;

import mango.sep3.databaseaccess.protobuf.Farm;
import mango.sep3.databaseaccess.protobuf.Offer;

import java.util.Collection;

public class DataContainer
{
  public Collection<Farm> farms;
  public Collection<Offer> offers;

  public Collection<Farm> getFarms()
  {
    return farms;
  }

  public void setFarms(Collection<Farm> farms)
  {
    this.farms = farms;
  }

  public Collection<Offer> getOffers()
  {
    return offers;
  }

  public void setOffers(Collection<Offer> offers)
  {
    this.offers = offers;
  }
}
