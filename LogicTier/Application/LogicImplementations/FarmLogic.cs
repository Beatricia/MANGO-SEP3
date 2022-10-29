using Application.LogicInterfaces;
using Shared.DTOs;
using Shared.Models;

namespace Application.LogicImplementations;
public class FarmLogic : IFarmLogic
{
    private FarmService.FarmServiceClient farmServiceClient;

    public FarmLogic(FarmService.FarmServiceClient farmServiceClient)
    {
        this.farmServiceClient = farmServiceClient;
    }
    
    /// <summary>
    /// Create asynchronously a Farm using the FarmCreationDto object. Call the CreateAsync method in
    /// the FarmDao class and return the created Farm object as a Task.
    /// </summary>
    /// <param name="dto">The object holding all the farm information</param>
    /// <returns>The created Farm object</returns>
    public async Task<Farm> CreateAsync(FarmCreationDto dto)
    {
        ValidateData(dto);
        Farm toCreate = new Farm
        {
            Name = dto.Name,
            Phone = dto.Phone,
            Zip = dto.ZIP,
            Address = dto.Address,
            City = dto.City,
            DeliveryDistance = dto.DeliveryDistance,
            FarmStatus = dto.FarmStatus,
        };

        Farm created = await farmServiceClient.CreateFarmAsync(toCreate);

        return created;
    }

    private void ValidateData(FarmCreationDto dto)
    {
        //todo should we check this in here or with the rest of the check from the db fx. city...
        string name = dto.Name;
        string phone = dto.Phone;

        if (name.Length < 1)
        {
            throw new Exception("Farm name must be longer!");
        }

        if (name.Length > 100)
        {
            throw new Exception("Farm name is too long!");
        }

        if (phone.Length != 8)
        {
            throw new Exception("Invalid phone number!");
        }
    }
}