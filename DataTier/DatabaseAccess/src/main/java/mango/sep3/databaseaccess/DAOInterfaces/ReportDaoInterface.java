package mango.sep3.databaseaccess.DAOInterfaces;

import mango.sep3.databaseaccess.Shared.Report;

import java.util.Collection;

public interface ReportDaoInterface
{
  Collection<Report> getReports();
  void deleteReport(long id);
  Report createReport(Report report);
  Report getReportById(long id);
  Collection<Report> getReportsByOfferId(int offerId);
}
