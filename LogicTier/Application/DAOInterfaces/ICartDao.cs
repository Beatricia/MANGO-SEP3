﻿using Shared.Models;

namespace Application.DAOInterfaces;

public interface ICartDao
{
    public Task AddToCartAsync(CartOfferDto dto);
    public Task<ICollection<CartOffer>> GetAllCartItemsAsync(string username);
    public Task DeleteAllCartOffersAsync(string username);
    public Task<CartOffer?> GetByIdAsync(int id);
    public Task DeleteCartOfferAsync (int id);
}