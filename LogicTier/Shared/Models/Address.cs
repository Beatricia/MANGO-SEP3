namespace Shared.Models;

public class Address
{
    public string City { get; set; } = "";
    
    public string Street { get; set; } = "";

    public string ZIP { get; set; } = "";
    
    public double Longitude { get; set; }
    public double Latitude { get; set; }
    
    public override string ToString()
    {
        return $"City: {City}, Zip: {ZIP}, Address: {Street} ";
    }


    
    /// <summary>
    /// Gets the distance between two addresses in kilometers.
    /// </summary>
    /// <example>
    /// <code>
    /// double distance = address1 - address2;
    /// </code>
    /// </example>
    /// <param name="a1"></param>
    /// <param name="a2"></param>
    /// <returns></returns>
    public static double operator -(Address a1, Address a2)
    {
        return GetDistance(a1, a2);
    }


    /// <summary>
    /// Calculates the distance between two addresses in kilometers
    /// </summary>
    /// <param name="a1"></param>
    /// <param name="a2"></param>
    /// <returns>Distance in kilometers</returns>
    public static double GetDistance(Address a1, Address a2)
    {
        return CalculateDistance(a1.Latitude, a1.Longitude, a2.Latitude, a2.Longitude);
    }
    
    /// <summary>
    /// Calculates the distance between two coordinates in kilometers.
    /// </summary>
    /// <param name="lat1">Latitude 1</param>
    /// <param name="lon1">Longitude 1</param>
    /// <param name="lat2">Latitude 2</param>
    /// <param name="lon2">Longitude 2</param>
    /// <returns>Distance in kilometers</returns>
    private static double CalculateDistance(double lat1, double lon1, double lat2, double lon2)
    {
        // a nice calculator to test: https://www.calculator.net/distance-calculator.html
        
        static double ToRadians(double degree) => (degree * Math.PI) / 180;
            
        lon1 = ToRadians(lon1);
        lon2 = ToRadians(lon2);
        lat1 = ToRadians(lat1);
        lat2 = ToRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.Pow(Math.Sin(dlat / 2), 2) +
                   Math.Cos(lat1) * Math.Cos(lat2) *
                   Math.Pow(Math.Sin(dlon / 2), 2);

        double c = 2 * Math.Asin(Math.Sqrt(a));

        // Radius of earth in
        // kilometers. Use 3956
        // for miles
        const double r = 6371;
            
        return (c * r);
    }
}