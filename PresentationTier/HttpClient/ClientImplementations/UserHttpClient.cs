using System.Net.Http.Json;
using System.Text.Json;
using HttpClient.ClientInterfaces;
using Shared.DTOs;
using Shared.Models;

namespace HttpClient.ClientImplementations;

public class UserHttpClient : IUserService
{
    private readonly System.Net.Http.HttpClient client;

    public UserHttpClient(System.Net.Http.HttpClient client)
    {
        this.client = client;
    }

    public async Task<Customer> GetCustomer(string username)
    {
        HttpResponseMessage response = await client.GetAsync($"/user/customer?username={username}");
        string content = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        Customer customer = JsonSerializer.Deserialize<Customer>(content, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;
        return customer;
    }

    public async Task UpdateCustomerAsync(CustomerUpdateDto dto)
    {
        HttpResponseMessage response = await client.PostAsJsonAsync("/user/customer", dto);
        if (!response.IsSuccessStatusCode)
        {
            string content = await response.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
    }
}