using Shared.Models;

namespace Application.LogicInterfaces;

public interface IOfferLogic
{
    Task CreateAsync(); //OfferCreation dto
    Task<IEnumerable<Offer>> GetAsync();
}