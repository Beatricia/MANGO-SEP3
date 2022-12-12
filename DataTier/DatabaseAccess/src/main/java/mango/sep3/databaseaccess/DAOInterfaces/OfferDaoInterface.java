package mango.sep3.databaseaccess.DAOInterfaces;


import mango.sep3.databaseaccess.Shared.Offer;

import java.util.Collection;

public interface OfferDaoInterface
{
  Offer createOffer(Offer offer);
  Collection<Offer> getOffer();
  Offer getOfferById(int id);
  Collection<Offer> getOffersByFarmName(String farmName);

  void disableOfferById(int id);
}
