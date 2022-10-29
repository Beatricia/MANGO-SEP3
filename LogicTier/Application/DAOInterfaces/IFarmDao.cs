using Shared.DTOs;
using Shared.Models;

namespace Application.DAOInterfaces;

public interface IFarmDao
{
    Task<Farm> CreteAsync(Farm farm);
}