using Application.LogicInterfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Shared.DTOs;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class UserController : ControllerBase
{
    private IUserLogic userLogic;

    public UserController(IUserLogic userLogic)
    {
        this.userLogic = userLogic;
    }

    [HttpGet("customer"), Authorize(Roles = "farmer")]
    public async Task<IActionResult> GetAsync([FromQuery] string username)
    {
        try
        {
            var returned = await userLogic.GetCustomer(username);
            return Ok(returned);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return BadRequest(e.Message);
        }
    }
    
    [HttpPost("customer"), /*Authorize(Roles = "customer")*/]
    public async Task<ActionResult> UpdateAsync(CustomerUpdateDto dto, string username)
    {
        try
        {
            await userLogic.UpdateCustomerAsync(dto, username);
            return Ok();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return BadRequest(e.Message);
        }
    }
}