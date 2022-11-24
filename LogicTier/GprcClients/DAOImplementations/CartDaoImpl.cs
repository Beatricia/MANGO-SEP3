﻿using Application.DAOInterfaces;
using Shared.Models;

namespace GprcClients.DAOImplementations;

public class CartDaoImpl : ICartDao
{
    private CartOfferService.CartOfferServiceClient cartOfferServiceClient;

    public CartDaoImpl(CartOfferService.CartOfferServiceClient cartOfferServiceClient )
    {
        this.cartOfferServiceClient = cartOfferServiceClient;
    }
    
    public async Task AddToCartAsync(CartOfferDto dto)
    {
        var toAddCartOffer = new CartOffer
        {
            OfferId = dto.OfferId,
            Quantity = dto.Quantity,
            Username = dto.Username,
            CollectionOption = dto.CollectOption
        };

        _ = await cartOfferServiceClient.AddToCartAsync(toAddCartOffer);
    }

    public async Task<ICollection<Shared.Models.CartOffer>> GetAllCartItemsAsync(string username)
    {
        var user = new Username
        {
            Username_ = username
        };

        // Getting the cartOffers from the database as CartOffers class from the buffer
        CartOffers cartOffers = await cartOfferServiceClient.GetAllCartOffersAsync(user);

        ICollection<Shared.Models.CartOffer> list = new List<Shared.Models.CartOffer>();

        foreach (var cartOffer in cartOffers.CartOffers_)
        {
            if (cartOffer is null)
                continue;
            //Creating a new instance of CartOffer from the Model
            Shared.Models.CartOffer cartOfferToSend = new Shared.Models.CartOffer
            {
                Id = cartOffer.Id,
                //Offer = cartOffer.Offer,
                Quantity = cartOffer.Quantity,
                UserName = cartOffer.Username,
                CollectionOption = cartOffer.CollectionOption
            };
            list.Add(cartOfferToSend);
        }

        return list;
    }
    
    public async Task DeleteAllCartOffersAsync(string username)
    {
        var user = new Username
        {
            Username_ = username
        };

       await cartOfferServiceClient.DeleteAllCartOffersAsync(user);
    }
}