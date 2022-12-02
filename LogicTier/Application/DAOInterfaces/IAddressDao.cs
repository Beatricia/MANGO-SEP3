using Shared.Models;

namespace Application.DAOInterfaces;

public interface IAddressDao
{
    
    /// <summary>
    /// Gets the coordinates of an address
    /// </summary>
    /// <param name="address"></param>
    /// <returns></returns>
    public Task<(double Latitude, double Longitude)> GetCoordinatesAsync(Address address);
}