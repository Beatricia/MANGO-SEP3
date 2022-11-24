using System.Net.Http.Json;
using System.Text.Json;
using HttpClient.ClientInterfaces;
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
       /* HttpResponseMessage response = await client.GetAsync($"/customer/{username}");
        string content = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        Customer customer = JsonSerializer.Deserialize<Customer>(content, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;
        return customer;*/
       Address address = new Address
       {
           City = "Horsens",
           Street = "Street name 5",
           ZIP = "8700"
       };

       Customer customer = new Customer
       {
           Address = address,
           FirstName = "Agata",
           LastName = "Rudol",
           Phone = "55215590",
           Username = "Agata"
       };
       return customer;
    }
}