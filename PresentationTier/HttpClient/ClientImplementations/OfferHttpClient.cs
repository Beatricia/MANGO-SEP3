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
    private readonly System.Net.Http.HttpClient client;

    public OfferHttpClient(System.Net.Http.HttpClient client)
    {
        this.client = client;
    } 
    ///summary///
    /// Sends POST request to a WebAPI server
    /// summary///
    public async Task<Offer?> CreateAsync(OfferCreationDto dto)
    {
        HttpResponseMessage response = await client.PostAsJsonAsync("/offer", dto);
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
    
    
    

    ///summary///
    /// Sends GET request to a WebAPI server
    /// summary///
    public async Task<ICollection<Offer>> GetAsync()
    {
        HttpResponseMessage response = await client.GetAsync("/offer");
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
        
        HttpResponseMessage response = await client.PostAsync($"/offer/{offerId}/image", imageContent);

        if (!response.IsSuccessStatusCode)
            throw new Exception("Failed to upload image.");
    }

    ///summary///
    /// Sends GET by farm name request to a WebAPI server
    /// summary///
    public async Task<ICollection<Offer>> GetByFarmNameAsync(string farmName)
    {
        HttpResponseMessage response = await client.GetAsync("/offer/farmName");
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
}