using System.Net.Http.Headers;
using HttpClient = System.Net.Http.HttpClient;

public class ApiAccess
{
    public string BaseApiAddress { get; set; } = "";
    public string JWT { get; set; } = "";

    private System.Net.Http.HttpClient _client;
    public System.Net.Http.HttpClient HttpClient
    {
        get
        {
            if (_client == null)
                _client = new()
                {
                    BaseAddress = new Uri(BaseApiAddress)
                };
            
            if(string.IsNullOrEmpty(JWT))
                _client.DefaultRequestHeaders.Authorization = AuthenticationHeaderValue.Parse("Bearer " + JWT);
            else
                _client.DefaultRequestHeaders.Authorization = null;
            
            return _client;
        }
    }
}