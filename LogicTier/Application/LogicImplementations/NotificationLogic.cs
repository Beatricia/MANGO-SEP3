using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Shared.DTOs;
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
    
    public Task AddNotificationAsync(NotificationCreationDto dto)
    {
        var not = new Notification
        {
            Text = dto.Text,
            ToUsername = dto.Username,
            CreatedAt = DateTime.Now
        };
        
        return notificationDao.AddNotificationsAsync(new List<Notification>{not});
    }
}