using Shared.DTOs;
using Shared.Models;

namespace Application.LogicInterfaces;

public interface INotificationLogic
{
    public Task<ICollection<Notification>> GetNotificationsAsync(string username);
    public Task AddNotificationAsync(NotificationCreationDto dto);
}