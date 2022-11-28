using Application.DAOInterfaces;

namespace GprcClients.DAOImplementations;

/// <summary>
/// Grpc implementation of the Authentication DAO
/// </summary>
public class AuthDaoImpl : IAuthDao
{
    private UserService.UserServiceClient client;
    
    public AuthDaoImpl(UserService.UserServiceClient client)
    {
        this.client = client;
    }
    
    public async Task<Shared.Models.UserAuth> RegisterAsync(Shared.Models.UserAuth user)
    {
        // create grpc UserAuth with username hash and salt
        var grpcUserAuth = new UserAuth
        {
            Username = user.Username,
            Hash = user.HashPassword,
            Salt = user.Salt
        };
        
        UserAuth grpcUser = await client.RegisterUserAsync(grpcUserAuth);
        
        return ConvertGrpcUserAuthToSharedUserAuth(grpcUser);
    }


    public async Task<Shared.Models.UserAuth?> GetAuthUserAsync(string username)
    {
        var text = new Text { Text_ = username };
        
        try
        {
            var userAuth = await client.GetUserAuthByUsernameAsync(text);
            return ConvertGrpcUserAuthToSharedUserAuth(userAuth);
        }
        catch { return null; }
    }
    

    public async Task<Shared.Models.User?> GetUserAsync(string username)
    {
        // create text object
        var text = new Text { Text_ = username };

        try
        {
            User user = await client.GetUserByUsernameAsync(text);
            return ConvertGrpcUserToSharedUser(user);
        }
        catch { return null; }
    }
    
    /// <summary>
    /// Converts a grpc User to a shared User
    /// </summary>
    /// <param name="grpcUser">the grpc user to convert</param>
    /// <returns></returns>
    private Shared.Models.User ConvertGrpcUserToSharedUser(User grpcUser)
    {
        var sharedUser = new Shared.Models.User
        {
            Username = grpcUser.Username,
            FirstName = grpcUser.Firstname,
            LastName = grpcUser.Lastname
        };
        
        return sharedUser;
    }
    
    /// <summary>
    /// Converts a grpc User to a shared User
    /// </summary>
    /// <param name="grpcUser">the grpc user to convert</param>
    /// <returns></returns>
    private Shared.Models.UserAuth ConvertGrpcUserAuthToSharedUserAuth(global::UserAuth grpcUser)
    {
        var sharedUserAuth = new Shared.Models.UserAuth()
        {
            Username = grpcUser.Username,
            Salt = grpcUser.Salt,
            HashPassword = grpcUser.Hash
        };
        
        return sharedUserAuth;
    }
    
    
    // convert shared farmer to grpc farmer
    private Farmer ConvertSharedToGrpc(Shared.Models.Farmer farmer)
    {
        var grpcFarmer = new Farmer
        {
            Username = farmer.Username,
            Firstname = farmer.FirstName,
            Lastname = farmer.LastName,
        };
        
        return grpcFarmer;
    }
    
    // convert grpc farmer to shared farmer
    private Shared.Models.Farmer ConvertGrpcToShared(Farmer farmer)
    {
        var sharedFarmer = new Shared.Models.Farmer
        {
            Username = farmer.Username,
            FirstName = farmer.Firstname,
            LastName = farmer.Lastname,
        };
        
        return sharedFarmer;
    }
    
    // convert shared customer to grpc customer
    private Customer ConvertSharedToGrpc(Shared.Models.Customer customer)
    {
        var grpcCustomer = new Customer
        {
            Username = customer.Username,
            Firstname = customer.FirstName,
            Lastname = customer.LastName,
            Address = ConvertSharedToGrpc(customer.Address),
            Phone = customer.Phone,
        };
        
        return grpcCustomer;
    }
    
    // convert grpc customer to shared customer
    private Shared.Models.Customer ConvertGrpcToShared(Customer customer)
    {
        var sharedCustomer = new Shared.Models.Customer
        {
            Username = customer.Username,
            FirstName = customer.Firstname,
            LastName = customer.Lastname,
            Phone = customer.Phone,
            Address = ConvertGrpcToShared(customer.Address)
        };
        
        return sharedCustomer;
    }
    
    private Address ConvertSharedToGrpc(Shared.Models.Address farmAddress)
    {
        return new Address
        {
            City = farmAddress.City,
            Street = farmAddress.Street,
            Zip = farmAddress.ZIP
        };
    }
    
    private Shared.Models.Address ConvertGrpcToShared(Address address)
    {
        return new Shared.Models.Address
        {
            City = address.City,
            Street = address.Street,
            ZIP = address.Zip
        };
    }
}