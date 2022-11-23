using System.Net.Http.Json;
using HttpClient.ClientInterfaces;
using Shared.DTOs;
using Shared.Models;

namespace HttpClient.ClientImplementations;

public class AuthHttpClient : IAuthService
{
    // accept httpclient from constructor to field
    private readonly System.Net.Http.HttpClient client;
    
    public AuthHttpClient(System.Net.Http.HttpClient httpClient)
    {
        client = httpClient;
    }
    
    
    public Task<User> LoginAsync(string username, string password)
    {
        throw new NotImplementedException();
    }

    public async Task<User> RegisterAsync(string username, string password, bool isFarmer)
    {
        RegisterDto dto = new RegisterDto()
        {
            Username = username,
            Password = password,
            IsFarmer = isFarmer
        };
        
        
        HttpResponseMessage response = await client.PostAsJsonAsync("/auth/register", dto);
        if (!response.IsSuccessStatusCode)
        {
            string content = await response.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
        
        var user = await response.Content.ReadFromJsonAsync<User>();
        if(user == null)
            throw new Exception("Cannot read user from response");
        
        return user;
    }
}