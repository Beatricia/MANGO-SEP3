using Shared.Models;

namespace HttpClient.ClientInterfaces;

public interface IReportService
{
    public Task<ICollection<Report>> GetAllReports();
}