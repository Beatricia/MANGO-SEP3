using System.Net.Http.Headers;
using System.Net.Http.Json;
using System.Text;
using System.Text.Json;
using System.Text.Json.Serialization;
using HttpClient.ClientInterfaces;
using Microsoft.AspNetCore.Components.Forms;
using Shared.DTOs;
using Shared.Models;
using JsonSerializer = System.Text.Json.JsonSerializer;


namespace HttpClient.ClientImplementations;

public class OfferHttpClient : IOfferService
{
    private System.Net.Http.HttpClient Client => apiAccess.HttpClient;
    private readonly ApiAccess apiAccess;
    public OfferHttpClient(ApiAccess apiAccess)
    {
        this.apiAccess = apiAccess;
    } 
    
    /// <summary>
    /// Sends POST request to a WebAPI server
    /// </summary>
    /// <param name="dto"></param>
    /// <returns></returns>
    /// <exception cref="Exception"></exception>
    public async Task<Offer?> CreateAsync(OfferCreationDto dto)
    {
        string dtoAsJson = JsonSerializer.Serialize(dto);
        StringContent body = new StringContent(dtoAsJson, Encoding.UTF8, "application/json");
        HttpResponseMessage response = await Client.PostAsJsonAsync("/offer", body);
        string content = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }
        
        return JsonSerializer.Deserialize<Offer>(content, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        });
    }
    
    
    

    /// <summary>
    /// Sends GET request to a WebAPI server
    /// </summary>
    /// <returns></returns>
    /// <exception cref="Exception"></exception>
    public async Task<ICollection<Offer>> GetAsync(string? username, int? distance, string? nameContains, bool delivery, bool pickUp, bool pickYo)
    {
        string query = ConstructQuery(username, distance, nameContains, delivery, pickUp, pickYo);
        
        
        HttpResponseMessage response = await Client.GetAsync("/offer"+query);
        string content = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        ICollection<Offer> offers = JsonSerializer.Deserialize<ICollection<Offer>>(content, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;
        return offers;
    }

    public async Task UploadImageAsync(IBrowserFile file, int offerId)
    {
        // upload image to path /offer/{offerId}/image
        
        await using Stream reader = file.OpenReadStream(file.Size);
        
        var imageContent = new MultipartFormDataContent();
        imageContent.Headers.ContentDisposition = new ContentDispositionHeaderValue("form-data");
        imageContent.Add(new StreamContent(reader, Convert.ToInt32(file.Size)), "image", file.Name);
        
        HttpResponseMessage response = await Client.PostAsync($"/offer/{offerId}/image", imageContent);

        if (!response.IsSuccessStatusCode)
            throw new Exception("Failed to upload image.");
    }

    /// <summary>
    /// Sends GET by farm name request to a WebAPI server
    /// </summary>
    /// <param name="farmName"></param>
    /// <returns></returns>
    /// <exception cref="Exception"></exception>
    public async Task<ICollection<Offer>> GetAsync(string farmName)
    {
        HttpResponseMessage response = await Client.GetAsync($"offer/{farmName}");
        string content = await response.Content.ReadAsStringAsync();

        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        ICollection<Offer> offers = JsonSerializer.Deserialize<ICollection<Offer>>(content, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;
        return offers;
    }

    /// <summary>
    /// Sends PATH request to a WebAPI server to set specific offer as disabled
    /// and to delete it from all the carts it is in
    /// </summary>
    /// <param name="id"></param>
    /// <returns></returns>
    public async Task DisableAsync(int id)
    {
        StringContent body = new StringContent("", Encoding.UTF8, "application/json");
        
        HttpResponseMessage response = await Client.PatchAsync($"/offer/{id}",body);
        if (!response.IsSuccessStatusCode)
        {
            string content = await response.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
    }

    public async Task<ICollection<Offer>> GetRecommendedOffers()
    {
        HttpResponseMessage response = await Client.GetAsync($"offer/recommended");
        string content = await response.Content.ReadAsStringAsync();

        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        ICollection<Offer> offers = JsonSerializer.Deserialize<ICollection<Offer>>(content, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;
        return offers;
    }

    private string ConstructQuery(string? username , int? distance, string? nameContains, bool delivery, bool pickUp, bool pickYo)
    {
        var list = new List<string>();
        
        if (!string.IsNullOrEmpty(username))
            list.Add($"username={username}");
        
        if (distance !=null && distance !=0)
            list.Add($"distance={distance}");
        
        if (!string.IsNullOrEmpty(nameContains))
            list.Add($"nameContains={nameContains}");
        
        if (delivery)
            list.Add($"delivery={delivery}");

        if (pickUp)
            list.Add($"pickUp={pickUp}");
        
        if (pickYo)
            list.Add($"pickYo={pickYo}");

        if (!list.Any())
            return "";

        return "?" + string.Join("&", list);
    }
}