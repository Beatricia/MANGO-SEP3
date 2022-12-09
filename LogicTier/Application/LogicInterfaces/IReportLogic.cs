using Shared.Models;

namespace Application.LogicInterfaces;

public interface IReportLogic
{
    Task<ICollection<Report>> GetAllReports();
}