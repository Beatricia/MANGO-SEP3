using Shared.Models;

namespace HttpClient.ClientInterfaces;

public interface INotificationService
{
    Task<ICollection<Notification>> GetNotifications();
    Task DeleteAsync(Notification notification);
}