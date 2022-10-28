using Shared.Models;

namespace HttpClient.ClientInterfaces;

public interface IOfferService
{
    Task CreateAsync(); //OfferCreation dto
    Task<ICollection<Offer>> GetAsync();
    
}