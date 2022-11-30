using Shared.DTOs;
using Shared.Models;

namespace Application.LogicInterfaces;

public interface IFarmLogic
{ 
    /// <summary>
    /// Create asynchronously a Farm using the FarmCreationDto object and return it as a Task
    /// </summary>
    /// <param name="dto">The object holding all the farm information</param>
    /// <returns>The created Farm object</returns>
    Task<Farm> CreateAsync(FarmCreationDto dto);

    Task<Farm> GetFarmByNameAsync(string farmName);

    /// <summary>
    /// Returns all the available icons.
    /// </summary>
    /// <returns></returns>
    ICollection<FarmIcon> GetAllIcons();
    
    Task<ICollection<Farm>> GetAllFarmsByFarmer(string username);
}