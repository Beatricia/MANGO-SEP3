﻿using System.Net.Http.Json;
using System.Text.Json;
using HttpClient.ClientInterfaces;
using Shared.Models;

namespace HttpClient.ClientImplementations;

public class CartHttpClients : ICartService
{
    private readonly System.Net.Http.HttpClient client;

    public CartHttpClients(System.Net.Http.HttpClient client)
    {
        this.client = client;
    }
    
    public async Task<User> AddToCartAsync(CartOfferDto dto)
    {
        HttpResponseMessage response = await client.PostAsJsonAsync("/cart", dto);
        string content = await response.Content.ReadAsStringAsync();

        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        User user = JsonSerializer.Deserialize<User>(content, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;

        return user;
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

    public async Task DeleteAllCartOffersAsync(string username)
    {
        HttpResponseMessage response = await client.DeleteAsync("/cart");
    }
}