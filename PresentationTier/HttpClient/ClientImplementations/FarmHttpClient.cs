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


    //send dto as body?
    public async Task CreateAsync(FarmCreationDto dto)
    {
        string dtoAsJson = JsonSerializer.Serialize(dto);
        StringContent body = new StringContent(dtoAsJson, Encoding.UTF8, "application/json");
        HttpResponseMessage response = await Client.PostAsJsonAsync("/farm", body);
        string content = await response.Content.ReadAsStringAsync();

        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }
    }

    public async Task<Farm> GetFarmByNameAsync(string farmName)
    {
        var client = Client;
        Console.WriteLine("Auth: " + client.DefaultRequestHeaders.Authorization);
        
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
        HttpResponseMessage response = await Client.GetAsync($"/farm/me"); //change /me to something more appropriate
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
        HttpResponseMessage response = await Client.GetAsync($"/farm/all");
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
        HttpResponseMessage response = await Client.GetAsync($"/farm/{nameContains}");
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
        string dtoAsJson = JsonSerializer.Serialize(dto);
        StringContent body = new StringContent(dtoAsJson, Encoding.UTF8, "application/json");
        HttpResponseMessage response = await Client.PostAsJsonAsync($"/farm/{farmName}/reviews", body);
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

    public async Task<Review> EditReviewAsync(long reviewId, string farmName, UpdateReviewDto dto)
    {
        string dtoAsJson = JsonSerializer.Serialize(dto);
        StringContent body = new StringContent(dtoAsJson, Encoding.UTF8, "application/json");
        HttpResponseMessage response = await Client.PatchAsync($"/farm/{farmName}/reviews/{reviewId}", body);
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

    public async Task DisableAsync(string farmName)
    {
        StringContent body = new StringContent("", Encoding.UTF8, "application/json");
        
        HttpResponseMessage response = await Client.PatchAsync($"/farm/{farmName}/disabled",body);
        if (!response.IsSuccessStatusCode)
        {
            string content = await response.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
    }

    public async Task<ICollection<Review>> GetAllReviews(string farmName)
    {
        
        HttpResponseMessage response = await Client.GetAsync($"/Farm/{farmName}/reviews");
        string content = await response.Content.ReadAsStringAsync();

        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        ICollection<Review> reviews = JsonSerializer.Deserialize<ICollection<Review>>(content,
            new JsonSerializerOptions
            {
                PropertyNameCaseInsensitive = true
            })!;
        return reviews;
    }
}