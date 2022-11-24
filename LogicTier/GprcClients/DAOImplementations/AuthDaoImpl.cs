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
    
    public async Task<Shared.Models.User> RegisterAsync(Shared.Models.UserAuth user)
    {
        // create grpc UserAuth with username hash and salt
        var grpcUserAuth = new UserAuth
        {
            Username = user.Username,
            Hash = user.HashPassword,
            Salt = user.Salt
        };
        
        User grpcUser = await client.RegisterUserAsync(grpcUserAuth);
        
        return ConvertGrpcUserToSharedUser(grpcUser);
    }

    public async Task<Shared.Models.User> LoginAsync(Shared.Models.UserAuth user)
    {
        var grpcUserAuth = new UserAuth
        {
            Username = user.Username,
            Hash = user.HashPassword,
            Salt = user.Salt
        };
        
        User grpcUser = await client.LoginUserAsync(grpcUserAuth);
        
        return ConvertGrpcUserToSharedUser(grpcUser);
    }

    public Task<Shared.Models.UserAuth?> GetAuthUserAsync(string username)
    {
        throw new NotImplementedException();
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
    
}