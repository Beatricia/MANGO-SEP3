using System.Text.Json;
using System.Web;
using Application.DAOInterfaces;

namespace WebAPI.Utils;

/// <summary>
/// Api website: https://positionstack.com
/// </summary>
public class AddressPositionStack : IAddressDao
{
    private HttpClient Client { get; }
    
    public AddressPositionStack(HttpClient client)
    {
        Client = client;
    }
    
    public async Task<(double Latitude, double Longitude)> GetCoordinatesAsync(Shared.Models.Address address)
    { 
        // get result from api

        string query = $"{address.City} {address.ZIP}, {address.Street}";
        using var result = await Client.GetAsync($"http://api.positionstack.com/v1/forward?access_key=7a05b023ffaf3864eb2d6f859a946876&query={HttpUtility.UrlEncode(query)}&country=dk");

        if (!result.IsSuccessStatusCode)
        {
            throw new Exception("Could not get coordinates, error " + result.StatusCode);
        }
        
        await using Stream stream = await result.Content.ReadAsStreamAsync(); // get result stream
        using JsonDocument jsonDoc = await JsonDocument.ParseAsync(stream); // parse stream to json

        using JsonElement.ArrayEnumerator data = jsonDoc.RootElement.GetProperty("data").EnumerateArray(); // get the data object

        if (data.MoveNext()) // if there is one element
        { // then get the latitude and longitude
           double latitude = data.Current.GetProperty("latitude").GetDouble();
           double longitude = data.Current.GetProperty("longitude").GetDouble();

           return (latitude, longitude);
        }
         
        // there was no element in the data, so throw an exception
        throw new Exception("Could not get coordinates, no data");
    }
}

/*
Sample response:

{
   "data":[
      {
         "latitude":55.869803,
         "longitude":9.842524,
         "type":"venue",
         "name":"Horsens - Nygade\/Vestergade",
         "number":null,
         "postal_code":null,
         "street":null,
         "confidence":1,
         "region":"Central Jutland",
         "region_code":"MJ",
         "county":null,
         "locality":"Horsens",
         "administrative_area":"Horsens",
         "neighbourhood":null,
         "country":"Denmark",
         "country_code":"DNK",
         "continent":"Europe",
         "label":"Horsens - Nygade\/Vestergade, Horsens, MJ, Denmark"
      },
      {
         "latitude":56.88381,
         "longitude":9.988584,
         "type":"address",
         "name":"Horsens Hedeg\u00e5rdsvej 3",
         "number":"3",
         "postal_code":"9520",
         "street":"Horsens Hedeg\u00e5rdsvej",
         "confidence":1,
         "region":"North Jutland",
         "region_code":"ND",
         "county":null,
         "locality":null,
         "administrative_area":"Rebild",
         "neighbourhood":null,
         "country":"Denmark",
         "country_code":"DNK",
         "continent":"Europe",
         "label":"Horsens Hedeg\u00e5rdsvej 3, Rebild, ND, Denmark"
      },
      {
         "latitude":56.895904,
         "longitude":10.009984,
         "type":"address",
         "name":"Horsens \u00d8sterg\u00e5rde 3",
         "number":"3",
         "postal_code":"9520",
         "street":"Horsens \u00d8sterg\u00e5rde",
         "confidence":1,
         "region":"North Jutland",
         "region_code":"ND",
         "county":null,
         "locality":null,
         "administrative_area":"Rebild",
         "neighbourhood":null,
         "country":"Denmark",
         "country_code":"DNK",
         "continent":"Europe",
         "label":"Horsens \u00d8sterg\u00e5rde 3, Rebild, ND, Denmark"
      },
      {
         "latitude":57.104731,
         "longitude":10.090346,
         "type":"address",
         "name":"Horsens Kirkevej 3",
         "number":"3",
         "postal_code":"9310",
         "street":"Horsens Kirkevej",
         "confidence":1,
         "region":"North Jutland",
         "region_code":"ND",
         "county":null,
         "locality":null,
         "administrative_area":"Aalborg",
         "neighbourhood":null,
         "country":"Denmark",
         "country_code":"DNK",
         "continent":"Europe",
         "label":"Horsens Kirkevej 3, Aalborg, ND, Denmark"
      },
      {
         "latitude":55.679254,
         "longitude":11.094668,
         "type":"address",
         "name":"Nygade 3",
         "number":"3",
         "postal_code":"4400",
         "street":"Nygade",
         "confidence":1,
         "region":"Zealand",
         "region_code":"SL",
         "county":null,
         "locality":"Kalundborg",
         "administrative_area":"Kalundborg",
         "neighbourhood":null,
         "country":"Denmark",
         "country_code":"DNK",
         "continent":"Europe",
         "label":"Nygade 3, Kalundborg, SL, Denmark"
      },
      {
         "latitude":55.678241,
         "longitude":12.573669,
         "type":"address",
         "name":"Nygade 3",
         "number":"3",
         "postal_code":"1164",
         "street":"Nygade",
         "confidence":1,
         "region":"Capital",
         "region_code":"HS",
         "county":null,
         "locality":"Copenhagen",
         "administrative_area":"Copenhagen",
         "neighbourhood":"Bispetorvet",
         "country":"Denmark",
         "country_code":"DNK",
         "continent":"Europe",
         "label":"Nygade 3, Copenhagen, HS, Denmark"
      },
      {
         "latitude":54.766154,
         "longitude":11.870228,
         "type":"address",
         "name":"Nygade 3",
         "number":"3",
         "postal_code":"4800",
         "street":"Nygade",
         "confidence":1,
         "region":"Zealand",
         "region_code":"SL",
         "county":null,
         "locality":"Nyk\u00f8bing Falster",
         "administrative_area":"Guldborgsund",
         "neighbourhood":null,
         "country":"Denmark",
         "country_code":"DNK",
         "continent":"Europe",
         "label":"Nygade 3, Nyk\u00f8bing Falster, SL, Denmark"
      },
      {
         "latitude":56.342595,
         "longitude":8.343303,
         "type":"address",
         "name":"Nygade 3",
         "number":"3",
         "postal_code":"7570",
         "street":"Nygade",
         "confidence":1,
         "region":"Central Jutland",
         "region_code":"MJ",
         "county":null,
         "locality":"Vemb",
         "administrative_area":"Holstebro",
         "neighbourhood":null,
         "country":"Denmark",
         "country_code":"DNK",
         "continent":"Europe",
         "label":"Nygade 3, Vemb, MJ, Denmark"
      },
      {
         "latitude":55.759679,
         "longitude":8.929513,
         "type":"address",
         "name":"Nygade 3",
         "number":"3",
         "postal_code":"7200",
         "street":"Nygade",
         "confidence":1,
         "region":"Southern",
         "region_code":"SD",
         "county":null,
         "locality":"Grindsted",
         "administrative_area":"Billund",
         "neighbourhood":null,
         "country":"Denmark",
         "country_code":"DNK",
         "continent":"Europe",
         "label":"Nygade 3, Grindsted, SD, Denmark"
      },
      {
         "latitude":56.370981,
         "longitude":9.660634,
         "type":"address",
         "name":"Nygade 3",
         "number":"3",
         "postal_code":"8850",
         "street":"Nygade",
         "confidence":1,
         "region":"Central Jutland",
         "region_code":"MJ",
         "county":null,
         "locality":"Bjerringbro",
         "administrative_area":"Viborg",
         "neighbourhood":null,
         "country":"Denmark",
         "country_code":"DNK",
         "continent":"Europe",
         "label":"Nygade 3, Bjerringbro, MJ, Denmark"
      }
   ]
}
*/