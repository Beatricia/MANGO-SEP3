using Shared.DTOs;
using Shared.Models;

namespace Application.LogicInterfaces;

/// <summary>
/// Logic interface for the farm
/// </summary>
public interface IOfferLogic
{
    Task<Offer> CreateAsync(OfferCreationDto dto); //OfferCreation dto
    Task<IEnumerable<Offer>> GetAsync();
    Task<IEnumerable<Offer>> GetByFarmNameAsync(string farmName);
}