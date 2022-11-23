using Application.LogicInterfaces;
using Microsoft.AspNetCore.Mvc;
using Shared.DTOs;

namespace WebAPI.Controllers;

[ApiController]
public class AuthController : ControllerBase
{
    private IAuthLogic authLogic;
    
    public AuthController(IAuthLogic authLogic)
    {
        this.authLogic = authLogic;
    }
    
    
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