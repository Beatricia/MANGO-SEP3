using Shared.Models;

namespace Application.LogicInterfaces;

public interface INotificationLogic
{
    public Task<ICollection<Notification>> GetNotificationsAsync(string username);
}