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
    private readonly IAddressDao addressDao;
    
    public AuthLogic(IAuthDao dao, IUserDao userDao, IAddressDao addressDao)
    {
        authDao = dao;
        this.userDao = userDao;
        this.addressDao = addressDao;
    }
    
    
    public async Task<User> RegisterAsync(RegisterDto dto)
    {
        string username = dto.Username;
        string passwordPlain = dto.Password;

        User? user = await authDao.GetUserAsync(username);
        
        // check if username is not more than 100 characters
        if (username.Length > 100)
            throw new ArgumentException("Username is too long");
        
        // check if password length at least 8 character
        if (passwordPlain.Length < 8)
            throw new Exception("Password must be at least 8 characters");
        
        // if user exists
        if (user != null)
            throw new Exception("Username already exists");
        
        
        var authUser = CreateUserAuth(dto.Username, dto.Password);
        
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
            
            (double latitude, double longitude) = await addressDao.GetCoordinatesAsync(address);
            
            address.Latitude = latitude;
            address.Longitude = longitude;
            
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

    public async Task RegisterAdminAsync(string pass)
    {
        var authUser = AuthLogic.CreateUserAuth("admin", pass);
        await authDao.RegisterAsync(authUser);
        await userDao.RegisterAdmin();
    }

    public async Task<bool> IsAdminRegistered()
    {
        var adminUser = await authDao.GetAuthUserAsync("admin");
        return adminUser is not null;
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

        user ??= await userDao.GetAdmin(username);
        if (user != null)
        {
            user.Role??= "admin";
        }


        if (user == null)
        {
            throw new Exception("Could not find user");
        }

        return user;
    }
    
    /// <summary>
    /// Creates a new user auth with a random generated salt and a hashed password from the generated salt.
    /// </summary>
    /// <param name="username"></param>
    /// <param name="pass"></param>
    /// <returns></returns>
    private static UserAuth CreateUserAuth(string username, string pass)
    {
        string saltString = CreateSalt();
        string hashPass = HashPassword(pass, saltString);

        return new UserAuth
        {
            Username = username,
            HashPassword = hashPass,
            Salt = saltString
        };
    }

    /// <summary>
    /// Creates a random salt
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