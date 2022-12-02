using Application.DAOInterfaces;
using GprcClients.Converters;

namespace GprcClients.DAOImplementations;

public class NotificationDaoImpl : INotificationDao
{
    // accept a notification grpc service in constructor
    private NotificationService.NotificationServiceClient notificationService;
    
    public NotificationDaoImpl(NotificationService.NotificationServiceClient notificationService)
    {
        this.notificationService = notificationService;
    }
    
    
    
    public async Task<ICollection<Shared.Models.Notification>> GetNotificationsByUserAsync(string username)
    {
        // create text object with username
        var text = username.ToGrpc();
        var stream =  notificationService.GetNotifications(text);
        
        var list = new List<Shared.Models.Notification>();
        // read stream
        while (await stream.ResponseStream.MoveNext(default))
        {
            var notification = stream.ResponseStream.Current.ToShared();
            list.Add(notification);
        }

        return list;
    }

    public async Task AddNotificationsAsync(ICollection<Shared.Models.Notification> notification)
    {
        var notifications = new RepeatedNotification();
        foreach (Shared.Models.Notification item in notification)
        {
            notifications.Notifications.Add(item.ToGrpc());
        }
        
        await notificationService.AddNotificationsAsync(notifications);
    }

    public async Task DeleteNotificationAsync(Shared.Models.Notification notification)
    {
        await notificationService.DeleteNotificationAsync(notification.ToGrpc());
    }

}