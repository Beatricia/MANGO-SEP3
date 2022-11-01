using Shared.Models;

namespace Application.LogicInterfaces;

public interface IOfferLogic
{
    Task CreateAsync(); //OfferCreation dto
    Task<IEnumerable<Shared.Models.Offer>> GetAsync();
}