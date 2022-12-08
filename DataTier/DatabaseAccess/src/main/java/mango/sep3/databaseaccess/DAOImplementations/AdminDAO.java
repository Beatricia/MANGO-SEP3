package mango.sep3.databaseaccess.DAOImplementations;

import mango.sep3.databaseaccess.DAOInterfaces.AdminDaoInterface;
import mango.sep3.databaseaccess.Repositories.AdminRepository;
import mango.sep3.databaseaccess.Repositories.ReportRepository;
import mango.sep3.databaseaccess.Shared.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class AdminDAO implements AdminDaoInterface
{
  @Autowired
  private AdminRepository adminRepository;

  @Autowired
  private ReportRepository reportRepository;

  public AdminDAO(){}

  @Override public Collection<Report> GetReports()
  {
    return reportRepository.findAll();
  }
}
