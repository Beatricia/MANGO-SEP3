﻿using Application.LogicInterfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Shared.DTOs;
using WebAPI.Controllers.Base;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class UserController : LocallyController
{
    private IUserLogic userLogic;

    public UserController(IUserLogic userLogic)
    {
        this.userLogic = userLogic;
    }

    //TODO authorized
    [HttpGet("customer")]
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
    
    //TODO authorized
    [HttpPost("customer")]
    public async Task<ActionResult> UpdateAsync(CustomerUpdateDto dto)
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