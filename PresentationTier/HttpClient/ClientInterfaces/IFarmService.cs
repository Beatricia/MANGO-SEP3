using Shared.DTOs;
using Shared.Models;

namespace HttpClient.ClientInterfaces;

public interface IFarmService
{
    Task CreateAsync(FarmCreationDto dto); //FarmCreationDto
    Task<Farm> GetFarmByNameAsync(string farmName);
}