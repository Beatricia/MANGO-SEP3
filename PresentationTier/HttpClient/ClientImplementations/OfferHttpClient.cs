using System.Net.Http.Json;
using System.Text.Json;
using HttpClient.ClientInterfaces;
using Shared.DTOs;
using Shared.Models;


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
    public async Task CreateAsync(OfferCreationDto dto)
    {
        HttpResponseMessage response = await client.PostAsJsonAsync("/offer", dto);
        if (!response.IsSuccessStatusCode)
        {
            string content = await response.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
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
}