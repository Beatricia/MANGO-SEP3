using Shared.Models;

namespace Application.DAOInterfaces;

public interface IFarmDao
{
    Task<Farm> CreteAsync();//FarmCreation dto
}