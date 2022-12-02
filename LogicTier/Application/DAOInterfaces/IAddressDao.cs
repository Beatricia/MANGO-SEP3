using Shared.Models;

namespace Application.DAOInterfaces;

public interface IAddressDao
{
    // Gets the coordinates of an address
    public Task<(double Latitude, double Longitude)> GetCoordinatesAsync(Address address);
}