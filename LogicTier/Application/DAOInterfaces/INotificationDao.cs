using Shared.Models;

namespace Application.DAOInterfaces;

public interface INotificationDao
{
    Task<ICollection<Notification>> GetNotificationsByUserAsync(string username);
    Task AddNotificationAsync(ICollection<Notification> notification);
}