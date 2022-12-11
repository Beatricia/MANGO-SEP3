using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Shared.DTOs;
using Shared.Models;

namespace Application.LogicImplementations;

public class ReportLogic : IReportLogic
{
    private IReportDao reportDao;
    private IOfferDao offerDao;
    private IFarmDao farmDao;
    private INotificationLogic notificationLogic;

    public ReportLogic(IReportDao reportDao, IOfferDao offerDao, IFarmDao farmDao,INotificationLogic notificationLogic)
    {
        this.reportDao = reportDao;
        this.offerDao = offerDao;
        this.farmDao = farmDao;
        this.notificationLogic = notificationLogic;
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

    public async Task NotifyFarmerAsync(long id)
    {
        var report =  await reportDao.GetReportById(id);
        var farm = await farmDao.GetFarmByNameAsync(report.Offer.FarmName);
        var farmerUsername = farm.Farmer.Username;

        var notification = new NotificationCreationDto
        {
            Username = farmerUsername,
            Text =
                $"Your offer has been reported because of {report.Reason}. Consider deleting the offer and checking the Locally Post Rules!",
        };
        await notificationLogic.AddNotificationAsync(notification);
        await DeleteReportAsync(id);
    }
}