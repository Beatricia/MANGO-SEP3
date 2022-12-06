using Shared.DTOs;
using Shared.Models;

namespace HttpClient.ClientInterfaces;

public interface IFarmService
{
    Task CreateAsync(FarmCreationDto dto); //FarmCreationDto
    Task<Farm> GetFarmByNameAsync(string farmName);
    /// <summary>
    /// Gets all the icons which can be assigned to farms
    /// </summary>
    /// <returns></returns>
    Task<ICollection<FarmIcon>> GetAllIconsAsync();

    Task<ICollection<Farm>?> GetAllFarmsByFarmerAsync();
    Task UpdateAsync(FarmUpdateDto dto);

    Task<ICollection<Farm>?> GetAllFarmsAsync();

    Task<ICollection<Farm>?> GetAllFarmsByNameContainsAsync(string nameContains);
    
    /// <summary>
    /// Creates a new review for a farm
    /// </summary>
    /// <param name="dto"></param>
    /// <returns></returns>
    Task<Review> CreateReviewAsync(string farmName, ReviewCreationDto dto);
}