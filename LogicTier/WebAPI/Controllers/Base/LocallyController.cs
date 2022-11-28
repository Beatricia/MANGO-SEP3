using System.Security.Claims;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers.Base;

public class LocallyController : ControllerBase
{
    public string? LoggedInUsername => User.Claims.Where(claim => claim.Type.Equals(ClaimTypes.Name)).Select(claim => claim.Value).FirstOrDefault();
}