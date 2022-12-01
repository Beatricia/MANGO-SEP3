using System.Security.Claims;
using Microsoft.Extensions.DependencyInjection;

namespace Shared.Auth;

public static class AuthorizationPolicies
{
    public static void AddPolicies(IServiceCollection services)
    {
        services.AddAuthorizationCore(options =>
        {
            //define our own policies below ---------------------------------------
            
            
            options.AddPolicy("MustBeFarmer", a =>
                a.RequireAuthenticatedUser().RequireClaim("Role", "farmer"));
                
                options.AddPolicy("MustBeCustomer", a =>
                a.RequireAuthenticatedUser().RequireClaim("Role", "customer"));
                
                options.AddPolicy("FarmerOrCustomer", a =>
                    a.RequireAuthenticatedUser().RequireRole("farmer", "customer"));
    /*
            options.AddPolicy("SecurityLevel2OrAbove", a =>
                a.RequireAuthenticatedUser().RequireAssertion(context =>
                {
                    Claim? levelClaim = context.User.FindFirst(claim => claim.Type.Equals("SecurityLevel"));
                    if (levelClaim == null) return false;
                    return int.Parse(levelClaim.Value) >= 2;
                }));
                */
        });
    }
}