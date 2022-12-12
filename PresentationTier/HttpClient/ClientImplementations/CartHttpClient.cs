using System.Net.Http.Json;
using System.Text;
using System.Text.Json;
using HttpClient.ClientInterfaces;
using Shared.DTOs;
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
        string dtoAsJson = JsonSerializer.Serialize(dto);
        StringContent body = new StringContent(dtoAsJson, Encoding.UTF8, "application/json");
        HttpResponseMessage response = await client.PostAsJsonAsync("/cart", body);
        string content = await response.Content.ReadAsStringAsync();

        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }
    }

    public async Task<ICollection<CartOffer>> GetAllCartItemsAsync()
    {
        HttpResponseMessage response = await client.GetAsync("/cart");
        string content = await response.Content.ReadAsStringAsync();

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
        HttpResponseMessage response = await client.DeleteAsync($"/cart");
        string content = await response.Content.ReadAsStringAsync();
        
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }
    }

    public async Task DeleteCartOfferAsync(int cartItemId)
    {
        HttpResponseMessage response = await client.DeleteAsync($"/cart/{cartItemId}");
        string content = await response.Content.ReadAsStringAsync();

        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }
    }

    public async Task UpdateCartOfferAsync(UpdateCartOfferDto dto)
    {
        string dtoAsJson = JsonSerializer.Serialize(dto);
        StringContent body = new StringContent(dtoAsJson, Encoding.UTF8, "application/json");
        HttpResponseMessage response = await client.PatchAsync("/cart", body);
        if (!response.IsSuccessStatusCode)
        {
            string content = await response.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
    }
}