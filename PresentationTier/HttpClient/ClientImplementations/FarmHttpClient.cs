using System.Net.Http.Json;
using HttpClient.ClientInterfaces;
using Shared.DTOs;
using Shared.Models;

namespace HttpClient.ClientImplementations;

public class FarmHttpClient : IFarmService
{
    private readonly System.Net.Http.HttpClient client;

    public FarmHttpClient(System.Net.Http.HttpClient client)
    {
        this.client = client;
    } 
    
    
    
    public async Task CreateAsync(FarmCreationDto dto)
    {
        HttpResponseMessage response = await client.PostAsJsonAsync("/farms", dto);
        string content = await response.Content.ReadAsStringAsync();
        
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }
    }
}