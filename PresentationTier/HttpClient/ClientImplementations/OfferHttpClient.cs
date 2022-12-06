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
    public async Task<ICollection<Offer>> GetAsync()
    {
        HttpResponseMessage response = await Client.GetAsync("/offer");
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
        HttpResponseMessage response = await Client.GetAsync($"/Offer/{farmName}");
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
}