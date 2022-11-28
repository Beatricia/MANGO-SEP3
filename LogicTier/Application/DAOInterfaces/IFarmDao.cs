using Shared.DTOs;
using Shared.Models;

namespace Application.DAOInterfaces;

public interface IFarmDao
{
    Task<Farm> CreateAsync(Farm farm);
    Task<Farm?> GetByName(string name);
}