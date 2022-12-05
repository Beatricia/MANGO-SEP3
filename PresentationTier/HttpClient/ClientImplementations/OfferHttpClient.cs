using System.Net.Http.Headers;
using System.Net.Http.Json;
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
        HttpResponseMessage response = await Client.PostAsJsonAsync("/offer", dto);
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
        HttpResponseMessage response = await Client.GetAsync($"offer/farmName?farmName={farmName}");
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
        string query = "";
        if (!string.IsNullOrEmpty(username))
        {
            query += $"?username={username}";
        }
        if (distance !=null && distance !=0)
        {
            query += string.IsNullOrEmpty(query) ? "?" : "&";
            query += $"?distance={distance}";
        }

        if (!string.IsNullOrEmpty(nameContains))
        {
            query += string.IsNullOrEmpty(query) ? "?" : "&";
            query += $"nameContains={nameContains}";
        }

        if (!delivery)
        {
            query += string.IsNullOrEmpty(query) ? "?" : "&";
            query += $"delivery={delivery}";
        }

        if (!pickUp)
        {
            query += string.IsNullOrEmpty(query) ? "?" : "&";
            query += $"pickUp={pickUp}";
        }
        if (!pickYo)
        {
            query += string.IsNullOrEmpty(query) ? "?" : "&";
            query += $"pickYo={pickYo}";
        }

        return query;
    }
}