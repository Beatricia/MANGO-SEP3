package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long>
{
    Collection<Report> getReportsByOfferId(int offerId);
}
