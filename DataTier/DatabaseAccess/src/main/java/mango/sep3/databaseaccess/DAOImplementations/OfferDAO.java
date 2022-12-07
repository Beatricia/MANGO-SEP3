package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.OfferDaoInterface;
import mango.sep3.databaseaccess.Repositories.FarmRepository;
import mango.sep3.databaseaccess.Shared.Farm;
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

  @Autowired
  private FarmRepository farmRepository;

  public OfferDAO() {
  }


  @Override
    public Offer CreateOffer(Offer offer) {
        offer.setFarm(farmRepository.findByName(offer.getFarm().getName()));
        offer = offerRepository.save(offer);
        return offer;
    }

  @Override
  public Collection<Offer> GetOffer() {
    return offerRepository.findAllByIsDisabledFalse();
  }

  @Override public Offer getOfferById(int id)
  {
    return offerRepository.findById(id).orElse(null);
  }

  @Override public Collection<Offer> getOffersByFarmName(String farmName)
  {
    Farm farm = farmRepository.findByName(farmName);
    return offerRepository.findAllByFarmAndIsDisabledFalse(farm);
  }

  @Override
  public void disableOfferById(int id) {
    Offer offer = offerRepository.findById(id).orElse(null);
    offer.setIsDisabled(true);
    offerRepository.save(offer);
  }
}
