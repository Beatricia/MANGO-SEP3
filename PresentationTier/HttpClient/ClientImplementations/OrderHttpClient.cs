using System.Net.Http.Json;
using System.Runtime.InteropServices;
using System.Text;
using System.Text.Json;
using HttpClient.ClientInterfaces;
using Shared.Models;

namespace HttpClient.ClientImplementations;

public class OrderHttpClient : IOrderService
{

    private System.Net.Http.HttpClient Client => apiAccess.HttpClient;
    private readonly ApiAccess apiAccess;
    public OrderHttpClient(ApiAccess apiAccess)
    {
        this.apiAccess = apiAccess;
    } 
    
    public async Task CreateOrderAsync()
    {
        HttpResponseMessage response = await Client.PostAsJsonAsync("/orders",string.Empty);
        if (!response.IsSuccessStatusCode)
        {
            string content = await response.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
    }

    public async Task<ICollection<Order>> GetAllOrdersAsync()
    {
        
        HttpResponseMessage response = await Client.GetAsync("/orders");
        string content = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        ICollection<Order> orders = JsonSerializer.Deserialize<ICollection<Order>>(content, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;
        return orders;
    }
    
    public async Task CompleteOrderAsync(int id)
    {
        StringContent body = new StringContent("", Encoding.UTF8, "application/json");
        
        HttpResponseMessage response = await Client.PatchAsync($"/orders/{id}",body);
        if (!response.IsSuccessStatusCode)
        {
            string content = await response.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
    }

    public async Task DeleteOrderAsync(int id)
    {
        Console.WriteLine("Delete order with id (http): " + id);
        HttpResponseMessage response = await Client.DeleteAsync($"orders/{id}");
        if (!response.IsSuccessStatusCode)
        {
            string content = await response.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
    }
}