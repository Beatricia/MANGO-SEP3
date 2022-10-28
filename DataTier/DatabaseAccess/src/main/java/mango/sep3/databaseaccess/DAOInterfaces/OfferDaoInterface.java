package mango.sep3.databaseaccess.DAOInterfaces;

import mango.sep3.databaseaccess.protobuf.Offer;

import java.util.Collection;

public interface OfferDaoInterface
{
  void CreateOffer(Offer offer);
  Collection<Offer> GetOffer();
}
