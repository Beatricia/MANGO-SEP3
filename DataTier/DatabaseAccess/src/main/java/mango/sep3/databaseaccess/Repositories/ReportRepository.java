package mango.sep3.databaseaccess.Repositories;

import mango.sep3.databaseaccess.Shared.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>
{
}
