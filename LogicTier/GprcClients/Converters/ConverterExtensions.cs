using Google.Protobuf;

namespace GprcClients.Converters;

/// <summary>
/// Extension methods to convert objects to and from protobuf messages.
/// </summary>
public static class ConverterExtensions
{

    #region Auth Converters

    
    public static UserAuth ToGrpc(this Shared.Models.UserAuth message)
    {
        return new UserAuth
        {
            Username = message.Username,
            Hash = message.HashPassword,
            Salt = message.Salt,
        };
    }
    
    public static Shared.Models.UserAuth ToShared(this UserAuth message)
    {
        return new Shared.Models.UserAuth
        {
            Username = message.Username,
            HashPassword = message.Hash,
            Salt = message.Salt,
        };
    }
    
    #endregion
}