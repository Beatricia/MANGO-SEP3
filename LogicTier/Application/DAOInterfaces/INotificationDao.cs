using Shared.Models;

namespace Application.DAOInterfaces;

public interface INotificationDao
{
    Task<ICollection<Notification>> GetNotificationsByUserAsync(string username);
    Task AddNotificationsAsync(ICollection<Notification> notification);
    Task DeleteNotificationAsync(Notification notification);
}