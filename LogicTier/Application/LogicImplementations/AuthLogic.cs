using System.Security.Cryptography;
using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Microsoft.AspNetCore.Cryptography.KeyDerivation;
using Shared.DTOs;
using Shared.Models;

namespace Application.LogicImplementations;

public class AuthLogic : IAuthLogic
{
    
    private readonly IAuthDao authDao;
    
    public AuthLogic(IAuthDao dao)
    {
        authDao = dao;
    }
    
    
    public async Task<User> RegisterAsync(RegisterDto dto)
    {
        string username = dto.Username;
        string passwordPlain = dto.Password;
        
        UserAuth? user = await authDao.GetAuthUserAsync(username);
        
        // if user exists
        if (user != null)
            throw new Exception("Username already exists");
        
        
        // check if password length at least 8 character
        if (passwordPlain.Length < 8)
            throw new Exception("Password must be at least 8 characters");

        string? saltString = CreateSalt();
        string hashPass = HashPassword(passwordPlain, saltString);

        var authUser = new UserAuth
        {
            Username = username,
            HashPassword = hashPass,
            Salt = saltString
        };

        return await authDao.RegisterAsync(authUser);
    }

    private static string CreateSalt()
    {
        byte[] salt = RandomNumberGenerator.GetBytes(128 / 8);
        return Convert.ToBase64String(salt);
    }
    
    private static string HashPassword(string password, string saltString)
    {
        // for more please check https://learn.microsoft.com/en-us/aspnet/core/security/data-protection/consumer-apis/password-hashing?view=aspnetcore-7.0

        byte[] salt = Convert.FromBase64String(saltString);
        
        // derive a 256-bit subkey (use HMACSHA256 with 100,000 iterations)
        string hashString = Convert.ToBase64String(KeyDerivation.Pbkdf2(
            password: password,
            salt: salt,
            prf: KeyDerivationPrf.HMACSHA256,
            iterationCount: 100000,
            numBytesRequested: 256 / 8));

        return hashString;
    }
}