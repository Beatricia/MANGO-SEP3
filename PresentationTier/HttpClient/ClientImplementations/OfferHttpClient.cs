using HttpClient.ClientInterfaces;
using Shared.Models;


namespace HttpClient.ClientImplementations;

public class OfferHttpClient : IOfferService
{
    /*private readonly HttpClient client;

    public OfferHttpClient(HttpClient client)
    {
        this.client = client;
    } */
    public Task CreateAsync()
    {
        throw new NotImplementedException();
    }

    public Task<ICollection<Offer>> GetAsync()
    {
        throw new NotImplementedException();
    }
}