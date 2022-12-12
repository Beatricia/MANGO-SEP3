using Shared.DTOs;
using Shared.Models;

namespace Application.LogicInterfaces;

public interface IReportLogic
{
    Task<ICollection<Report>> GetAllReports();
    Task DeleteReportAsync(long id);
    Task<Report> ReportOfferAsync(ReportCreationDto reportDto);
    Task NotifyFarmerAsync(long id);
}