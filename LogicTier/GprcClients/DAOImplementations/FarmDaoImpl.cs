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
            IconPath = farm.FarmIcon.FileName,
        };

        _ = await farmServiceClient.CreateFarmAsync(toCreate);
        
        return farm;
    }

    public async Task<Shared.Models.Farm?> GetByName(string name)
    {
        var text = new Text{Text_= name};
        var grpcFarm = await farmServiceClient.GetFarmByNameAsync(text);

        return ConvertToSharedFarm(grpcFarm);
    }
    
    
    // convert grpc farm to shared farm
    private Shared.Models.Farm ConvertToSharedFarm(Farm grpcFarm)
    {
        return new Shared.Models.Farm
        {
            Name = grpcFarm.Name,
            Phone = grpcFarm.Phone,
            Address = new Shared.Models.Address
            {
                ZIP = grpcFarm.Zip,
                Street = grpcFarm.Address,
                City = grpcFarm.City
            },
            DeliveryDistance = grpcFarm.DeliveryDistance,
            FarmStatus = grpcFarm.FarmStatus
        };
    }
}