package mango.sep3.databaseaccess.DAOInterfaces;


import mango.sep3.databaseaccess.Shared.Offer;

import java.util.Collection;

public interface OfferDaoInterface
{
  Offer CreateOffer(Offer offer);
  Collection<Offer> GetOffers();
  Offer getOfferById(int id);
}
