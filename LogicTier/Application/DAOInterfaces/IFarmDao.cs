using System.Collections.ObjectModel;
using Shared.DTOs;
using Shared.Models;

namespace Application.DAOInterfaces;

public interface IFarmDao
{
    Task<Farm> CreateAsync(Farm farm);
    Task<Farm> GetFarmByNameAsync(string farmName);
    Task<Farm?> GetByName(string name);

    Task<ICollection<Farm>> GetAllFarmsByFarmer(string username);


    Task UpdateFarmAsync(FarmUpdateDto dto);
    Task<ICollection<string>> GetAllCustomersUncompletedOrder(string farmName);
    Task<ICollection<Farm>> GetAllAsync();
    Task<ICollection<Farm>> GetAllByNameAsync(string nameContains);
}