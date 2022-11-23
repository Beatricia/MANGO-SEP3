using Application.LogicInterfaces;
using Microsoft.AspNetCore.Mvc;
using Shared.DTOs;

namespace WebAPI.Controllers;

/// <summary>
/// Authentication controller.
/// </summary>
[ApiController]
[Route("[controller]")]
public class AuthController : ControllerBase
{
    private IAuthLogic authLogic;
    
    public AuthController(IAuthLogic authLogic)
    {
        this.authLogic = authLogic;
    }
    
    
    /// <summary>
    /// Registers a new user to the system
    /// </summary>
    /// <param name="dto">user registration data</param>
    /// <returns></returns>
    [HttpPost("register")]
    public async Task<IActionResult> Register(RegisterDto dto)
    {
        try
        {
            var user = await authLogic.RegisterAsync(dto);
            return Ok(user);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return BadRequest(e.Message);
        }
    }
}