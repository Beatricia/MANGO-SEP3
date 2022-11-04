using Shared.DTOs;
using Shared.Models;

namespace Application.LogicInterfaces;

/// <summary>
/// Logic interface for the farm
/// </summary>
public interface IOfferLogic
{
    Task<Shared.Models.Offer> CreateAsync(OfferCreationDto dto); //OfferCreation dto
    Task<IEnumerable<Shared.Models.Offer>> GetAsync();
}