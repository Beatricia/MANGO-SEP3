package mango.sep3.databaseaccess.DAOInterfaces;

import mango.sep3.databaseaccess.Shared.Report;

import java.util.Collection;

public interface AdminDaoInterface
{
  Collection<Report> GetReports();
}
