using System.Net.Http.Headers;
using System.Security.Claims;
using HttpClient.Utils;
using HttpClient = System.Net.Http.HttpClient;

/// <summary>
/// This class is responsible to provide the correct HttpClient to make requests to a Web Api.
/// </summary>
public class ApiAccess
{
    /// <summary>
    /// Base address to the web API
    /// </summary>
    public string BaseApiAddress { get; set; } = "";
    /// <summary>
    /// JWT authentication token to use when connecting to the API
    /// </summary>
    public string JWT { get; private set; } = "";

    private System.Net.Http.HttpClient? _client;
    /// <summary>
    /// Gets the HttpClient instance ready to make a request to the API
    /// </summary>
    public System.Net.Http.HttpClient HttpClient
    {
        get
        {
            if (_client == null)
            {
                _client = new()
                {
                    BaseAddress = new Uri(BaseApiAddress)
                };
            }

            if(!string.IsNullOrEmpty(JWT))
                _client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", JWT);
            else
                _client.DefaultRequestHeaders.Authorization = null;
            
            return _client;
        }
    }

    
    private readonly Blazored.LocalStorage.ILocalStorageService localStorageService;
    private const string CookieKey = "jwt";
    
    public ApiAccess(Blazored.LocalStorage.ILocalStorageService localStorageService)
    {
        this.localStorageService = localStorageService;
        Setup();
    }

    /// <summary>
    /// Sets up the class by getting the cookie from the localstorage
    /// </summary>
    private async Task Setup()
    {
        var jwtToken = await localStorageService.GetItemAsStringAsync(CookieKey);
        
        if (jwtToken is { Length: > 0 })
        {
            JWT = jwtToken;
        }
    }

    public async Task ManualSetupAsync()
    {
        var jwtToken = await localStorageService.GetItemAsStringAsync(CookieKey);
        
        if (jwtToken is { Length: > 0 })
        {
            JWT = jwtToken;
        }
    }

    /// <summary>
    /// Logs in the current client by saving the token in the localstorage
    /// </summary>
    /// <param name="token"></param>
    public async Task LoginAsync(string token)
    {
        JWT = token;
        await localStorageService.SetItemAsStringAsync(CookieKey, token);
    }

    public async Task LogoutAsync()
    {
        await localStorageService.RemoveItemAsync(CookieKey);
    }
}