package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.Farm;
import mango.sep3.databaseaccess.Shared.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
  Collection<Offer> findAllByIsDisabledFalse();
  Collection<Offer>  findAllByFarmAndIsDisabledFalse(Farm farm);
}
