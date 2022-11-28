using HttpClient.ClientImplementations;
using Microsoft.AspNetCore.Components.Forms;
using Shared.DTOs;
using Shared.Models;

namespace HttpClient.ClientInterfaces;

public interface IOfferService
{
    /// <summary>
    /// Sends POST request to a WebAPI server
    /// </summary>
    /// <param name="dto"></param>
    /// <returns></returns>
    Task<Offer?> CreateAsync(OfferCreationDto dto);
    /// <summary>
    /// Gets all the offers
    /// </summary>
    /// <returns></returns>
    Task<ICollection<Offer>> GetAsync();
    /// <summary>
    /// Uploads an image for an offer
    /// </summary>
    /// <param name="file"></param>
    /// <param name="offerId"></param>
    /// <returns></returns>
    Task UploadImageAsync(IBrowserFile file, int offerId);
    
}