using System.Text.Json;
using System.Web;
using Application.DAOInterfaces;

namespace WebAPI.Utils;

public class AddressLocationIQ : IAddressDao
{
    private HttpClient Client { get; }
    
    // dotnet user-secrets set "LocationIq:ApiKey" "secret"
    private IConfiguration configuration; // this is for accessing the api key
    
    public AddressLocationIQ(HttpClient client, IConfiguration configuration)
    {
        Client = client;
        this.configuration = configuration;
    }
    
    public async Task<(double Latitude, double Longitude)> GetCoordinatesAsync(Shared.Models.Address address)
    { 
        // get result from api

        string query = $"Denmark {address.City} {address.Street}"; 
        string apiKey = configuration["LocationIq:ApiKey"];
        string requestUrl = $"https://eu1.locationiq.com/v1/search?key={apiKey}&q={HttpUtility.UrlEncode(query)}&format=json";

        using HttpResponseMessage result = await Client.GetAsync(requestUrl);

        if (!result.IsSuccessStatusCode)
        {
            throw new ArgumentException("Could not get coordinates, error " + result.StatusCode);
        }
        
        await using Stream stream = await result.Content.ReadAsStreamAsync(); // get result stream
        using JsonDocument jsonDoc = await JsonDocument.ParseAsync(stream); // parse stream to json

        JsonElement? data = jsonDoc.RootElement.EnumerateArray().FirstOrDefault(); // get the data object

        if (data is not null) 
        { 
            double latitude = double.Parse(data.Value.GetProperty("lat").GetString()!);
            double longitude = double.Parse(data.Value.GetProperty("lon").GetString()!);

            return (latitude, longitude);
        }
         
        // there was no element in the data, so throw an exception
        throw new Exception("Could not get coordinates, no data");
    }
}

/*
 Sample response:
 
 [
   {
      "place_id":"140365205",
      "licence":"https:\/\/locationiq.com\/attribution",
      "osm_type":"way",
      "osm_id":"142158552",
      "boundingbox":[
         "56.135602",
         "56.137385",
         "10.1785277",
         "10.1847903"
      ],
      "lat":"56.1364799",
      "lon":"10.1819198",
      "display_name":"Rolfsgade, Kongsvang, Tegh\u00f8j, Aarhus, Aarhus Municipality, Central Denmark Region, 8000, Denmark",
      "class":"highway",
      "type":"residential",
      "importance":0.32000999999999996
   }
]
*/