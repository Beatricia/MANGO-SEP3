using System.Net.Http.Json;
using System.Text;
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
        HttpResponseMessage response = await Client.GetAsync($"/farm/{farmName}");
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

    public async Task<ICollection<Farm>?> GetAllFarmsByFarmerAsync()
    {
        HttpResponseMessage response = await Client.GetAsync($"/Farm/me");
        string content = await response.Content.ReadAsStringAsync();

        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        ICollection<Farm> farms = JsonSerializer.Deserialize<ICollection<Farm>>(content,
            new JsonSerializerOptions
            {
                PropertyNameCaseInsensitive = true
            })!;
        return farms;
    }

    public async Task UpdateAsync(FarmUpdateDto dto)
    {
        string dtoAsJson = JsonSerializer.Serialize(dto);
        StringContent body = new StringContent(dtoAsJson, Encoding.UTF8, "application/json");
        HttpResponseMessage response = await Client.PatchAsync("/farm", body);
        if (!response.IsSuccessStatusCode)
        {
            string content = await response.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
    }

    public async Task<ICollection<Farm>?> GetAllFarmsAsync()
    {
        HttpResponseMessage response = await Client.GetAsync($"/Farm/all");
        string content = await response.Content.ReadAsStringAsync();

        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        ICollection<Farm> farms = JsonSerializer.Deserialize<ICollection<Farm>>(content,
            new JsonSerializerOptions
            {
                PropertyNameCaseInsensitive = true
            })!;
        return farms;
    }

    public async Task<ICollection<Farm>?> GetAllFarmsByNameContainsAsync(string nameContains)
    {
        HttpResponseMessage response = await Client.GetAsync($"/Farm/{nameContains}");
        string content = await response.Content.ReadAsStringAsync();

        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        ICollection<Farm> farms = JsonSerializer.Deserialize<ICollection<Farm>>(content,
            new JsonSerializerOptions
            {
                PropertyNameCaseInsensitive = true
            })!;
        return farms;
    }

    public async Task<Review> CreateReviewAsync(string farmName, ReviewCreationDto dto)
    {
        HttpResponseMessage response = await Client.PostAsJsonAsync($"/farm/{farmName}/reviews", dto);
        string content = await response.Content.ReadAsStringAsync();

        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }
        
        Review review = JsonSerializer.Deserialize<Review>(content, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;
        
        return review;
    }
}