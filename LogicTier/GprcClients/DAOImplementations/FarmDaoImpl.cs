using Application.DAOInterfaces;
using Address = Shared.Models.Address;

namespace GprcClients.DAOImplementations;

public class FarmDaoImpl : IFarmDao
{
    private FarmService.FarmServiceClient farmServiceClient;

    public FarmDaoImpl(FarmService.FarmServiceClient farmServiceClient)
    {
        this.farmServiceClient = farmServiceClient;
    }

    public async Task<Shared.Models.Farm> CreateAsync(Shared.Models.Farm farm)
    {
        var toCreate = new Farm()
        {
            Name = farm.Name,
            Phone = farm.Phone,
            Zip = farm.Address.ZIP,
            Address = farm.Address.Street,
            City = farm.Address.City,
            DeliveryDistance = farm.DeliveryDistance,
            FarmStatus = farm.FarmStatus,
        };

        await farmServiceClient.CreateFarmAsync(toCreate);
        
        return farm;
    }

    public async Task<Shared.Models.Farm> GetFarmByNameAsync(string farmName)
    {
        var name = new Text
        {
            Text_ = farmName
        };

        Farm createdFarm = await farmServiceClient.GetFarmAsync(name);

        Shared.Models.Address address = new Shared.Models.Address
        {
            City = createdFarm.City,
            Street = createdFarm.Address,
            ZIP = createdFarm.Zip
        };

        Shared.Models.Farm farm = new Shared.Models.Farm
        {
            Address = address,
            DeliveryDistance = createdFarm.DeliveryDistance,
            FarmStatus = createdFarm.FarmStatus,
            Name = createdFarm.Name,
            Phone = createdFarm.Phone
        };

        return farm;
    }
}