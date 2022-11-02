using System.Net.Http.Json;
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
    public async Task CreateAsync(OfferCreationDto dto)
    {
        HttpResponseMessage response = await client.PostAsJsonAsync("/offers", dto);
        if (!response.IsSuccessStatusCode)
        {
            string content = await response.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
    }

    public Task<ICollection<Offer>> GetAsync()
    {
        throw new NotImplementedException();
    }
}