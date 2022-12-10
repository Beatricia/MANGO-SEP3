using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Shared.DTOs;
using Shared.Models;

namespace Application.LogicImplementations;

public class ReportLogic : IReportLogic
{
    private IReportDao reportDao;
    private IOfferDao offerDao;

    public ReportLogic(IReportDao reportDao, IOfferDao offerDao)
    {
        this.reportDao = reportDao;
        this.offerDao = offerDao;
    }
    
    public Task<ICollection<Report>> GetAllReports()
    {
        return reportDao.GetAllReportsAsync();
    }

    public async Task DeleteReportAsync(long id)
    {
        await reportDao.DeleteReportAsync(id);
    }

    public async Task<Report> ReportOfferAsync(ReportCreationDto reportDto)
    {
        Offer? offer = await offerDao.GetOfferByIdAsync(reportDto.OfferId);
        if (offer is null)
        {
            throw new Exception($"Offer {reportDto.OfferId} not found");
        }

        var report = new Report
        {
            Offer = offer,
            Reason = reportDto.Reason,
        };

        return await reportDao.CreateReportAsync(report);
    }
}