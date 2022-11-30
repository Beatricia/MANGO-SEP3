using Application.DAOInterfaces;

namespace GprcClients.DAOImplementations;

public class FarmDaoImpl : IFarmDao
{
    private FarmService.FarmServiceClient farmServiceClient;
    private IFarmIconDao farmIconDao;

    public FarmDaoImpl(FarmService.FarmServiceClient farmServiceClient, IFarmIconDao farmIconDao)
    {
        this.farmServiceClient = farmServiceClient;
        this.farmIconDao = farmIconDao;
    }

    public async Task<Shared.Models.Farm> CreateAsync(Shared.Models.Farm farm)
    {
        var toCreate = new Farm
        {
            Name = farm.Name,
            Phone = farm.Phone,
            DeliveryDistance = farm.DeliveryDistance,
            FarmStatus = farm.FarmStatus,
            Farmer = ConvertFarmerToGrpc(farm.Farmer),
            Address = ConvertSharedToGrpc(farm.Address),
            IconPath = farm.FarmIcon.FileName
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

        Farm farmGrpc = await farmServiceClient.GetFarmAsync(name);
        global::Address addressGrpc = farmGrpc.Address;
        
        
        Shared.Models.Address address = new Shared.Models.Address
        {
            City = addressGrpc.City,
            Street = addressGrpc.Street,
            ZIP = addressGrpc.Zip
        };

        Shared.Models.Farm farm = new Shared.Models.Farm
        {
            Address = address,
            DeliveryDistance = farmGrpc.DeliveryDistance,
            FarmStatus = farmGrpc.FarmStatus,
            Name = farmGrpc.Name,
            Phone = farmGrpc.Phone,
            Farmer = ConvertFarmerFromGrpc(farmGrpc.Farmer)
        };

        return farm;
    }

    private Shared.Models.Farmer ConvertFarmerFromGrpc(global::Farmer farmGrpcFarmer)
    {
        var farmer = new Shared.Models.Farmer
        {
            FirstName = farmGrpcFarmer.Firstname,
            LastName = farmGrpcFarmer.Lastname,
            Username = farmGrpcFarmer.Username
        };
        return farmer;
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
                ZIP = grpcFarm.Address.Zip,
                Street = grpcFarm.Address.Street,
                City = grpcFarm.Address.City
            },
            DeliveryDistance = grpcFarm.DeliveryDistance,
            FarmStatus = grpcFarm.FarmStatus,
            Farmer = ConvertFarmerFromGrpc(grpcFarm.Farmer),
            FarmIcon = farmIconDao.CreateIcon(grpcFarm.IconPath!),
        };
    }
    
    private Farmer ConvertFarmerToGrpc(Shared.Models.Farmer farmFarmer)
    {
        var farmer = new global::Farmer
        {
            Firstname = farmFarmer.FirstName,
            Lastname = farmFarmer.LastName,
            Username = farmFarmer.Username
        };
        return farmer;
    }
    
    private Address ConvertSharedToGrpc(Shared.Models.Address farmAddress)
    {
        return new Address
        {
            City = farmAddress.City,
            Street = farmAddress.Street,
            Zip = farmAddress.ZIP
        };
    }
}