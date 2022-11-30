using System.Net.Http.Json;
using System.Text;
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

    public async Task<Farmer> GetFarmer(string username)
    {
        HttpResponseMessage response = await client.GetAsync($"/user/farmer?username={username}");
        string content = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        Farmer farmer = JsonSerializer.Deserialize<Farmer>(content, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;
        return farmer;
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
        string dtoAsJson = JsonSerializer.Serialize(dto);
        StringContent body = new StringContent(dtoAsJson, Encoding.UTF8, "application/json");
        HttpResponseMessage response = await client.PatchAsync("/user/customer", body);
        if (!response.IsSuccessStatusCode)
        {
            string content = await response.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
    }
}