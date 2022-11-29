using System.Net.Http.Json;
using System.Text.Json;
using HttpClient.ClientInterfaces;
using Shared.DTOs;
using Shared.Models;

namespace HttpClient.ClientImplementations;

public class FarmHttpClient : IFarmService
{
    private System.Net.Http.HttpClient Client => apiAccess.HttpClient;
    private readonly ApiAccess apiAccess;
    public FarmHttpClient(ApiAccess apiAccess)
    {
        this.apiAccess = apiAccess;
    } 
    
    
    
    public async Task CreateAsync(FarmCreationDto dto)
    {
        HttpResponseMessage response = await Client.PostAsJsonAsync("/farm", dto);
        string content = await response.Content.ReadAsStringAsync();
        
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }
    }

    public async Task<Farm> GetFarmByNameAsync(string farmName)
    {
        HttpResponseMessage response = await Client.GetAsync($"/farm?farmName={farmName}");
        string content = await response.Content.ReadAsStringAsync();
        
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }
        
        Farm farm = JsonSerializer.Deserialize<Farm>(content, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;
    

        return farm;
    }

    public async Task<ICollection<FarmIcon>> GetAllIconsAsync()
    {
        var response = await Client.GetAsync("/farm/icons");
        var content = await response.Content.ReadAsStringAsync();
        
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        var deserialized = JsonSerializer.Deserialize<ICollection<FarmIcon>>(content, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        });
        
        return deserialized ?? new List<FarmIcon>(); 
    }
}