using Application.DAOInterfaces;

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
        var toCreate = new Farm
        {
            Name = farm.Name,
            Phone = farm.Phone,
            Zip = farm.Address.ZIP,
            Address = farm.Address.Street,
            City = farm.Address.City,
            DeliveryDistance = farm.DeliveryDistance,
            FarmStatus = farm.FarmStatus,
            IconPath = farm.FarmIcon
        };

        _ = await farmServiceClient.CreateFarmAsync(toCreate);
        
        return farm;
    }
}