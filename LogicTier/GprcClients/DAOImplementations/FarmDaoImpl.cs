using System.Collections;
using System.Collections.ObjectModel;
using Application.DAOInterfaces;
using GprcClients.Converters;
using Grpc.Core;
using Shared.DTOs;

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
        var toCreate = farm.ToGrpc();
        await farmServiceClient.CreateFarmAsync(toCreate);
        
        return farm;
    }



    public async Task<Shared.Models.Farm?> GetFarmByNameAsync(string farmName)
    {
        try
        {
            var name = farmName.ToGrpc();
            Farm farmGrpc = await farmServiceClient.GetFarmAsync(name);

            return farmGrpc.ToShared(farmIconDao);
        }
        catch
        {
            return null;
        }
    }

    public async Task UpdateFarmAsync(FarmUpdateDto dto)
    {
        var farmUpdate = new FarmUpdate
        {
            Name = dto.Name,
            Phone = dto.FarmPhone,
            Status = dto.FarmStatus
        };
        
        try
        {
            await farmServiceClient.UpdateFarmAsync(farmUpdate);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }

    public async Task<ICollection<Shared.Models.Farm>> GetAllFarmsByFarmer(string username)
    {
        var user = new Farmer()
        {
            Username = username
        };   
        
        //Getting the farms from the database as buffers
        Farms farmsBuff = farmServiceClient.GetFarms(user);

        ICollection<Shared.Models.Farm> list = new List<Shared.Models.Farm>();

        foreach (var farm in farmsBuff.Farms_)
        {
            var farmShared = farm.ToShared(farmIconDao);
            list.Add(farmShared);
        }

        return list;
    }
    public async Task<ICollection<string>> GetAllCustomersUncompletedOrder(string farmName)
    {
        var text = farmName.ToGrpc();
        
        var usernamesGrpc = await farmServiceClient.GetAllCustomersUncompletedOrderAsync(text);

        return usernamesGrpc.ToShared();
    }

    public async Task<ICollection<Shared.Models.Farm>> GetAllAsync()
    {
        
        //Getting the farms from the database as buffers
        Farms farmsBuff = farmServiceClient.GetAllFarms(new Void());

        ICollection<Shared.Models.Farm> list = new List<Shared.Models.Farm>();

        foreach (var farm in farmsBuff.Farms_)
        {
            var farmShared = farm.ToShared(farmIconDao);
            list.Add(farmShared);
        }

        return list;
    }

    public async Task<ICollection<Shared.Models.Farm>> GetAllByNameAsync(string nameContains)
    {
        var text = new Text
        {
            Text_ = nameContains
        };   
        
        //Getting the farms from the database as buffers
        Farms farmsBuff = farmServiceClient.GetFarmsByName(text);

        ICollection<Shared.Models.Farm> list = new List<Shared.Models.Farm>();

        foreach (var farm in farmsBuff.Farms_)
        {
            var farmShared = farm.ToShared(farmIconDao);
            list.Add(farmShared);
        }

        return list;
    }

    public async Task DisableAsync(string farmName)
    {
        var text = new Text
        {
            Text_ = farmName
        };   

        await farmServiceClient.DisableFarmByNameAsync(text);
    }


    public async Task<Shared.Models.Farm?> GetByName(string name)
    {
        var text = new Text{Text_= name};
        var grpcFarm = await farmServiceClient.GetFarmByNameAsync(text);

        return grpcFarm.ToShared(farmIconDao);
    }
}