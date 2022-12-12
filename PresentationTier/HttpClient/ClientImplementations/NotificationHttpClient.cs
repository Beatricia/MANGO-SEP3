using System.Net.Http.Json;
using System.Text.Json;
using HttpClient.ClientInterfaces;
using Shared.Models;

namespace HttpClient.ClientImplementations;

public class NotificationHttpClient : INotificationService
{
    private System.Net.Http.HttpClient Client => apiAccess.HttpClient;
    private readonly ApiAccess apiAccess;
    
    public NotificationHttpClient(ApiAccess apiAccess)
    {
        this.apiAccess = apiAccess;
    } 

    
    
    public async Task<ICollection<Notification>> GetNotifications()
    {
        var text = await Client.GetStringAsync("notifications");
        
        var notifications = JsonSerializer.Deserialize<ICollection<Notification>>(text, new JsonSerializerOptions()
        {
            PropertyNameCaseInsensitive = true
        });
        
        if(notifications is null)
            throw new Exception("Could not deserialize notifications");

        return notifications;
    }

    public Task DeleteAsync(Notification notification)
    {
        HttpRequestMessage request = new HttpRequestMessage
        {
            Content = JsonContent.Create(notification),
            Method = HttpMethod.Delete,
            RequestUri = new Uri("notifications", UriKind.Relative)
        };
        return Client.SendAsync(request);
    }
}