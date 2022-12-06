using Shared.DTOs;
using Shared.Models;

namespace Application.DAOInterfaces;

/// <summary>
/// Interface responsible for accessing data related to Offers
/// </summary>
public interface IOfferDao
{
    Task<Offer> CreateAsync(Offer offer); 
    
    /// <summary>
    /// Getting the Offers from the database
    /// </summary>
    /// <returns> A Collection of Offers </returns>
    Task<IEnumerable<Offer>> GetAsync();

    Task<Offer> GetOfferByIdAsync(int id);
    Task<IEnumerable<Offer>> GetByFarmNameAsync(string farmName);
    
    Task DisableAsync(int id);
}