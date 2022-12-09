using Shared.Models;

namespace Application.DAOInterfaces;

public interface IReportDao
{
    Task<ICollection<Report>> GetAllReports();
    Task DeleteReportAsync(long id);
}