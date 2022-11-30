using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Shared.Models;

namespace Application.LogicImplementations;

public class NotificationLogic : INotificationLogic
{
    private INotificationDao notificationDao;
    
    
    public NotificationLogic(INotificationDao notificationDAO)
    {
        notificationDao = notificationDAO;
    }

    public Task<ICollection<Notification>> GetNotificationsAsync(string username)
    {
        return notificationDao.GetNotificationsByUserAsync(username);
    }
}