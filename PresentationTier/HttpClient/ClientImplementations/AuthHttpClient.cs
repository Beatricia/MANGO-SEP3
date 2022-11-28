using System.Net.Http.Json;
using System.Security.Claims;
using System.Text.Json;
using HttpClient.ClientInterfaces;
using Shared.DTOs;
using Shared.Models;

namespace HttpClient.ClientImplementations;

/// <summary>
/// Http implementation of the <see cref="IAuthService"/> interface.
/// </summary>
public class AuthHttpClient : IAuthService
{
    // accept httpclient from constructor to field
    private System.Net.Http.HttpClient Client => _access.HttpClient;
    public static string? Jwt { get; private set; } = "";

    private readonly ApiAccess _access;
    public AuthHttpClient(ApiAccess apiAccess)
    {
        _access = apiAccess;
    }
    
    /// <inheritdoc/>
    public async Task<User> LoginAsync(string username, string password)
    {
        LoginDto dto = new LoginDto()
        {
            Username = username,
            Password = password
        };
        
        
        HttpResponseMessage response = await Client.PostAsJsonAsync("/auth/login", dto);
        if (!response.IsSuccessStatusCode)
        {
            string content = await response.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
        
        var loginResponse = await response.Content.ReadFromJsonAsync<LoginResponse>();
        if(loginResponse == null)
            throw new Exception("Cannot read user from response");


        await _access.LoginAsync(loginResponse.Token);
        
        //was returning Login response but isnt User better?
        return loginResponse.User;
    }

    /// <inheritdoc/>
    public async Task<User> RegisterAsync(string username, string password, bool isFarmer)
    {
        RegisterDto dto = new RegisterDto()
        {
            Username = username,
            Password = password,
            IsFarmer = isFarmer
        };
        
        
        HttpResponseMessage response = await Client.PostAsJsonAsync("/auth/register", dto);
        if (!response.IsSuccessStatusCode)
        {
            string content = await response.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
        
        var user = await response.Content.ReadFromJsonAsync<User>();
        if(user == null)
            throw new Exception("Cannot read user from response");
        
        return user;
    }

    public Task<ClaimsPrincipal> GetAuthAsync()
    {
        ClaimsPrincipal principal = CreateClaimsPrincipal();
        return Task.FromResult(principal);  
    }

    public Action<ClaimsPrincipal> OnAuthStateChanged { get; set; }


    // Below methods stolen from https://github.com/SteveSandersonMS/presentation-2019-06-NDCOslo/blob/master/demos/MissionControl/MissionControl.Client/Util/ServiceExtensions.cs
    private static IEnumerable<Claim> ParseClaimsFromJwt(string jwt)
    {
        string payload = jwt.Split('.')[1];
        byte[] jsonBytes = ParseBase64WithoutPadding(payload);
        Dictionary<string, object>? keyValuePairs = JsonSerializer.Deserialize<Dictionary<string, object>>(jsonBytes);
        return keyValuePairs!.Select(kvp => new Claim(kvp.Key, kvp.Value.ToString()!));
    }
    
    
    private static ClaimsPrincipal CreateClaimsPrincipal()
    {
        if (string.IsNullOrEmpty(Jwt))
        {
            return new ClaimsPrincipal();
        }

        IEnumerable<Claim> claims = ParseClaimsFromJwt(Jwt);
    
        ClaimsIdentity identity = new(claims, "jwt");

        ClaimsPrincipal principal = new(identity);
        return principal;
    }

    private static byte[] ParseBase64WithoutPadding(string base64)
    {
        switch (base64.Length % 4)
        {
            case 2:
                base64 += "==";
                break;
            case 3:
                base64 += "=";
                break;
        }

        return Convert.FromBase64String(base64);
    }

    
}