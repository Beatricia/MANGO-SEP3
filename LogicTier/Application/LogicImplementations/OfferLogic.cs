using Application.DAOInterfaces;
using Application.LogicInterfaces;

namespace Application.LogicImplementations;

public class OfferLogic : IOfferLogic
{
    private OfferService

    public OfferLogic(IOfferDao offerDao)
    {
        this.offerDao = offerDao;
    }
    
    public Task CreateAsync()
    {
        throw new NotImplementedException();
    }

    public Task<IEnumerable<Offer>> GetAsync()
    {
        return offerDao.GetAsync();
    }
}