using Shared.DTOs;

namespace HttpClient.ClientInterfaces;

public interface IFarmService
{
    Task CreateAsync(FarmCreationDto dto); //FarmCreationDto
}