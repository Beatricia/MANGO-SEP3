using Application.LogicInterfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Shared.DTOs;
using WebAPI.Controllers.Base;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class UsersController : LocallyController
{
    private IUserLogic userLogic;

    public UsersController(IUserLogic userLogic)
    {
        this.userLogic = userLogic;
    }

    //TODO authorized
    [HttpGet("customer/{username}")]
    public async Task<IActionResult> GetCustomerAsync([FromRoute] string username)
    {
        Console.WriteLine(username);
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
    
    [HttpGet("farmer/{username}")]
    public async Task<IActionResult> GetFarmerAsync([FromRoute] string username)
    {
        try
        {
            var returned = await userLogic.GetFarmer(username);
            return Ok(returned);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return BadRequest(e.Message);
        }
    }
    
    //TODO authorized
    [HttpPatch("customer")]
    public async Task<ActionResult> UpdateAsync([FromBody]CustomerUpdateDto dto)
    {
        string username = LoggedInUsername;
        
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