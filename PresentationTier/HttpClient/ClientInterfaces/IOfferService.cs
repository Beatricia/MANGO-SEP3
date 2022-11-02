using HttpClient.ClientImplementations;
using Shared.DTOs;
using Shared.Models;

namespace HttpClient.ClientInterfaces;

public interface IOfferService
{
    ///summary///
    /// Sends POST request to a WebAPI server
    /// summary///
    Task CreateAsync(OfferCreationDto dto);
    Task<ICollection<Offer>> GetAsync();
    
}