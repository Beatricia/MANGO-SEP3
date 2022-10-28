using Shared.Models;

namespace Application.LogicInterfaces;

public interface IFarmLogic
{
    Task<Farm> CreteAsync();//FarmCreation dto
}