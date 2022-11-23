using Application.DAOInterfaces;

namespace GprcClients.DAOImplementations;

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
    
    // create method to convert grpc user to shared User with username first and last name
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