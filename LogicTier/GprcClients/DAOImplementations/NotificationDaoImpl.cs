﻿using Application.DAOInterfaces;

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

    public async Task AddNotificationsAsync(ICollection<Shared.Models.Notification> notification)
    {
        var notifications = new RepeatedNotification();
        foreach (Shared.Models.Notification item in notification)
        {
            notifications.Notifications.Add(ConvertNotification(item));
        }
        
        await notificationService.AddNotificationsAsync(notifications);
    }

    public async Task DeleteNotificationAsync(Shared.Models.Notification notification)
    {
        await notificationService.DeleteNotificationAsync(ConvertNotification(notification));
    }


    // convert grpc notification to shared
    private Shared.Models.Notification ConvertNotification(Notification notification)
    {
        return new Shared.Models.Notification
        {
            Id = notification.Id,
            Text = notification.Text,
            CreatedAt = new DateTime(notification.CreatedAt),
            ToUsername = notification.ToUsername
        };
    }
    
    // convert shared notification to grpc
    private Notification ConvertNotification(Shared.Models.Notification notification)
    {
        return new Notification
        {
            Id = notification.Id,
            Text = notification.Text,
            CreatedAt = notification.CreatedAt.Ticks,
            ToUsername = notification.ToUsername
        };
    }
}