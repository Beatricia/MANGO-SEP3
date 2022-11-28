package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.OfferDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import mango.sep3.databaseaccess.DAOInterfaces.OfferDaoInterface;
import mango.sep3.databaseaccess.Repositories.OfferRepository;
import mango.sep3.databaseaccess.Shared.Offer;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class OfferDAO implements OfferDaoInterface {
  @Autowired
  private OfferRepository offerRepository;

  public OfferDAO() {
  }



  @Override
  public mango.sep3.databaseaccess.Shared.Offer CreateOffer(Offer offer) {
    return offerRepository.save(offer);
  }

  @Override
  public Collection<Offer> GetOffer() {
    return offerRepository.findAll();
  }

  @Override public Offer getOfferById(int id)
  {
    return offerRepository.getReferenceById(id);
  }
}