namespace Application.DAOInterfaces;

public interface IOfferDao
{
    Task CreateAsync(); //OfferCreation dto
    Task<IEnumerable<Offer>> GetAsync();
}