using Application.DAOInterfaces;
using GprcClients.Converters;

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
        var grpcUserAuth = user.ToGrpc();
        UserAuth grpcUser = await client.RegisterUserAsync(grpcUserAuth);
        
        return grpcUser.ToShared();
    }


    public async Task<Shared.Models.UserAuth?> GetAuthUserAsync(string username)
    {
        var text = username.ToGrpc();
        
        try
        {
            var userAuth = await client.GetUserAuthByUsernameAsync(text);
            return userAuth.ToShared();
        }
        catch { return null; }
    }
    

    public async Task<Shared.Models.User?> GetUserAsync(string username)
    {
        // create text object
        var text = username.ToGrpc();

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