package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.ReportDaoInterface;
import mango.sep3.databaseaccess.Repositories.ReportRepository;
import mango.sep3.databaseaccess.Shared.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class ReportDAO implements ReportDaoInterface
{
  @Autowired
  private ReportRepository reportRepository;

  public ReportDAO(){}

  @Override public Collection<Report> getReports()
  {
    return reportRepository.findAll();
  }

  @Override public void deleteReport(long id)
  {
    reportRepository.deleteById(id);
  }

  @Override
  public Report createReport(Report report) {
    return reportRepository.save(report);
  }

  @Override public Report getReportById(long id)
  {
    return reportRepository.findById(id).orElse(null);
  }

  @Override
  public Collection<Report> getReportsByOfferId(int offerId) {
    return reportRepository.getReportsByOfferId(offerId);
  }
}
