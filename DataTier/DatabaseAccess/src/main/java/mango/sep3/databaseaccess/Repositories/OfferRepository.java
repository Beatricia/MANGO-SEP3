package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
}
