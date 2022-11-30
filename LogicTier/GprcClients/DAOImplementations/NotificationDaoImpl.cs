using Application.DAOInterfaces;

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
        var text = new Text { Text_ = username };
        var stream =  notificationService.GetNotifications(text);
        
        var list = new List<Shared.Models.Notification>();
        // read stream
        while (await stream.ResponseStream.MoveNext(default))
        {
            var notification = ConvertNotification(stream.ResponseStream.Current);
            list.Add(notification);
        }

        return list;
    }

    public async Task AddNotification(ICollection<Shared.Models.Notification> notification)
    {
        var notifications = new RepeatedNotification();
        foreach (Shared.Models.Notification item in notification)
        {
            notifications.Notifications.Add(ConvertNotification(item));
        }
        
        await notificationService.AddNotificationsAsync(notifications);
    }
    
    
    
    // convert grpc notification to shared
    private Shared.Models.Notification ConvertNotification(Notification notification)
    {
        return new Shared.Models.Notification
        {
            Text = notification.Text,
            CreatedAt = new DateTime(2),
            ToUsername = notification.ToUsername
        };
    }
    
    // convert shared notification to grpc
    private Notification ConvertNotification(Shared.Models.Notification notification)
    {
        return new Notification
        {
            Text = notification.Text,
            CreatedAt = notification.CreatedAt.Ticks,
            ToUsername = notification.ToUsername
        };
    }
}