using Shared.Models;

namespace Application.DAOInterfaces;

public interface IOfferDao
{
    Task CreateAsync(Offer offer); 
    Task<IEnumerable<Offer>> GetAsync();
}