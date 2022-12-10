using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Shared.Models;

namespace Application.LogicImplementations;

public class ReportLogic : IReportLogic
{
    private IReportDao reportDao;

    public ReportLogic(IReportDao reportDao)
    {
        this.reportDao = reportDao;
    }
    
    public Task<ICollection<Report>> GetAllReports()
    {
        return reportDao.GetAllReports();
    }

    public async Task DeleteReportAsync(long id)
    {
        await reportDao.DeleteReportAsync(id);
    }

    public Task ReportOfferAsync(int offerId)
    {
        throw new NotImplementedException();
    }
}