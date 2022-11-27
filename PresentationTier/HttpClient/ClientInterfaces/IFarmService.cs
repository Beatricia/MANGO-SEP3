using Shared.DTOs;
using Shared.Models;

namespace HttpClient.ClientInterfaces;

public interface IFarmService
{
    Task CreateAsync(FarmCreationDto dto); //FarmCreationDto
    /// <summary>
    /// Gets all the icons which can be assigned to farms
    /// </summary>
    /// <returns></returns>
    Task<ICollection<FarmIcon>> GetAllIconsAsync();
}