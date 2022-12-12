using Shared.Models;

namespace Application.DAOInterfaces;

public interface IReportDao
{
    Task<ICollection<Report>> GetAllReportsAsync();
    Task DeleteReportAsync(long id);
    Task<Report> CreateReportAsync(Report report);
    Task<Report> GetReportById(long id);
}