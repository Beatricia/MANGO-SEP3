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
    Task<ICollection<Offer>> GetAsync(string? username , int? distance, string? nameContains, bool delivery, bool pickUp, bool pickYo);
    /// <summary>
    /// Uploads an image for an offer
    /// </summary>
    /// <param name="file"></param>
    /// <param name="offerId"></param>
    /// <returns></returns>
    Task UploadImageAsync(IBrowserFile file, int offerId);

    /// <summary>
    /// Gets all offers from a specific farm
    /// </summary>
    /// <param name="farmName"></param>
    /// <returns></returns>
    Task<ICollection<Offer>> GetAsync(string farmName);

    /// <summary>
    /// Disables specific offer
    /// </summary>
    /// <param name="id"></param>
    /// <returns></returns>
    Task DisableAsync(int id);

    /// <summary>
    /// Gets the recommended offers for the currently logged in user
    /// </summary>
    /// <returns></returns>
    Task<ICollection<Offer>> GetRecommendedOffers();

}