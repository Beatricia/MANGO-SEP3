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
    private readonly IUserDao userDao;
    
    public AuthLogic(IAuthDao dao, IUserDao userDao)
    {
        authDao = dao;
        this.userDao = userDao;
    }
    
    
    public async Task<User> RegisterAsync(RegisterDto dto)
    {
        string username = dto.Username;
        string passwordPlain = dto.Password;

        User? user = await authDao.GetUserAsync(username);
        
        // if user exists
        if (user != null)
            throw new Exception("Username already exists");
        
        
        // check if password length at least 8 character
        if (passwordPlain.Length < 8)
            throw new Exception("Password must be at least 8 characters");

        string saltString = CreateSalt();
        string hashPass = HashPassword(passwordPlain, saltString);

        var authUser = new UserAuth
        {
            Username = username,
            HashPassword = hashPass,
            Salt = saltString
        };
        
        _ = await authDao.RegisterAsync(authUser);


        if (dto.IsFarmer)
        {
            Farmer farmer = new Farmer
            {
                Username = dto.Username,
                LastName = dto.FirstName,
                FirstName = dto.LastName,
                Role = "farmer",
            };
            return await userDao.RegisterFarmer(farmer);
        }
        else
        {
            Address address = new Address(){Street = dto.Street, City = dto.City, ZIP = dto.ZIP};
            
            Customer customer = new Customer()
            {
                Username = dto.Username,
                LastName = dto.FirstName,
                FirstName = dto.LastName,
                Address = address,
                Phone = dto.PhoneNumber,
                Role = "customer"
            };

            
            return await userDao.RegisterCustomer(customer);
        }
    }

    /// <summary>
    /// Check if user exists, checks if password is correct -> logs user in
    /// </summary>
    /// <returns></returns>
    public async Task<User> LoginAsync(LoginDto dto)
    {
        string username = dto.Username;
        string passwordPlain = dto.Password;
        
        //get logged user
        var authUser = await authDao.GetAuthUserAsync(username);

        // check if user exists
        if (authUser == null)
            throw new Exception("User does not exist");

        
        string saltString = authUser.Salt;
        string hashPassToCheck = HashPassword(passwordPlain, saltString);

        
        if (hashPassToCheck != authUser.HashPassword)
        {
            throw new Exception("Password does not match");
        }

        User? user = await userDao.GetCustomer(username);
        if (user != null)
        {
           user.Role = "customer"; 
        }
        user ??= await userDao.GetFarmer(username);
        if (user != null)
        {
            user.Role??= "farmer";
        }
        

        return user;
    }

    /// <summary>
    /// Create salt for password
    /// </summary>
    /// <returns></returns>
    private static string CreateSalt()
    {
        byte[] salt = RandomNumberGenerator.GetBytes(128 / 8);
        return Convert.ToBase64String(salt);
    }
    
    /// <summary>
    /// Hash password with salt
    /// </summary>
    /// <param name="password">the password to hash</param>
    /// <param name="saltString">salt value to hash with</param>
    /// <returns></returns>
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