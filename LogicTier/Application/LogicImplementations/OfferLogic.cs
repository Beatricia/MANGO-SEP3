﻿using Application.LogicInterfaces;
using Shared.DTOs;
using Offer = Shared.Models.Offer;

namespace Application.LogicImplementations;

public class OfferLogic : IOfferLogic
{
    private OfferService.OfferServiceClient offerService;

    public OfferLogic(OfferService.OfferServiceClient offerService)
    {
        this.offerService = offerService;
    }
    
    /// <summary>
    /// Create asynchronously a Offer using the OfferCreationDto object. Call the CreateAsync method in gRPC client 
    /// </summary>
    /// <param name="dto">The object holding all the offer information</param>
    /// <returns>The created Offer object</returns>
    public async Task<Shared.Models.Offer> CreateAsync(OfferCreationDto dto)
    {
        ValidateData(dto);
        var id = 1;

        //TODO make correct id 


        var offerToCreate = new global::Offer
        {
            Id = id,
            Name = dto.Name,
            Quantity = dto.Quantity,
            Unit = dto.Unit,
            Price = dto.Price,
            Delivery = dto.Delivery,
            PickUp = dto.PickUp,
            PickYourOwn = dto.PickYourOwn,
            Description = dto.Description,
            ImagePath = dto.ImagePath
        };

        global::Offer created = await offerService.CreateOfferAsync(offerToCreate);
        Shared.Models.Offer offerToSend = new Shared.Models.Offer
        {
            Id = created.Id,
            Name = created.Name,
            Quantity = created.Quantity,
            Unit = created.Unit,
            Price = created.Price,
            Delivery = created.Delivery,
            PickUp = created.PickUp,
            PickYourOwn = created.PickYourOwn,
            Description = created.Description,
            ImagePath = created.ImagePath
        };
        return offerToSend;

    }

    public Task<IEnumerable<global::Offer>> GetAsync()
    {
        throw new NotImplementedException();
    }


     private void ValidateData(OfferCreationDto dto)
    {
        
        if (dto.Name.Length > 100)
        {
            throw new Exception("Offer name is too long!");
        }

        if (dto.Quantity <= 0)
        {
            throw new Exception("Quantity must be bigger than 0!");
        }

        if (dto.Price <= 0)
        {
            throw new Exception("Price must be bigger than 0!");
        }

    }
}