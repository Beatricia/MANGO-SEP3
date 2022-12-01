using System.Net.Http.Json;
using System.Text.Json;
using HttpClient.ClientInterfaces;
using Shared.Models;

namespace HttpClient.ClientImplementations;

public class CartHttpClient : ICartService
{
    private System.Net.Http.HttpClient client => _apiAccess.HttpClient;
    private ApiAccess _apiAccess;
    public CartHttpClient(ApiAccess access)
    {
        _apiAccess = access;
    }
    
    public async Task AddToCartAsync(CartOfferDto dto)
    {
        HttpResponseMessage response = await client.PostAsJsonAsync("/Cart", dto);
        string content = await response.Content.ReadAsStringAsync();

        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }
    }

    public async Task<ICollection<CartOffer>> GetAllCartItemsAsync()
    {
        HttpResponseMessage response = await client.GetAsync("/Cart");
        string content = await response.Content.ReadAsStringAsync();
        Console.WriteLine(content);

        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        ICollection<CartOffer> cartOffers = JsonSerializer.Deserialize<ICollection<CartOffer>>(content,
            new JsonSerializerOptions
            {
                PropertyNameCaseInsensitive = true
            })!;
        return cartOffers;
    }

    public async Task DeleteAllCartOffersAsync()
    {
        HttpResponseMessage response = await client.DeleteAsync($"/Cart");
        string content = await response.Content.ReadAsStringAsync();
        
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }
    }

    public async Task DeleteCartOfferAsync(int cartItemId)
    {
        HttpResponseMessage response = await client.DeleteAsync($"/Cart/{cartItemId}");
        string content = await response.Content.ReadAsStringAsync();

        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }
    }
}