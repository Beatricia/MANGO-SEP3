using HttpClient.ClientImplementations;
using Shared.DTOs;
using Shared.Models;

namespace HttpClient.ClientInterfaces;

public interface IOfferService
{
    Task CreateAsync(OfferCreationDto dto); //OfferCreation dto
    Task<ICollection<Offer>> GetAsync();
    
}