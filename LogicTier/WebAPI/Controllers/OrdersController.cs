using Application.LogicInterfaces;
using Grpc.Core;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Shared.Models;
using WebAPI.Controllers.Base;

namespace WebAPI.Controllers;

[ApiController] [Route("[controller]")]
//[Authorize]
public class OrdersController : LocallyController
{
    private readonly IOrderLogic orderLogic;

    public OrdersController(IOrderLogic orderLogic)
    {
        this.orderLogic = orderLogic;
    }
    
    [HttpPost]
    public async Task<IActionResult> CreateAsync()
    {
        string username = LoggedInUsername;
        try
        {
            await orderLogic.CreateOrderAsync(username);
            return Ok();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500,e.Message);
        }
    }
    
    [HttpGet]
    public async Task<IActionResult> GetAsync()
    {
        string username = LoggedInUsername;
        try
        {
            var created = await orderLogic.GetAllOrders(username);
            
            return Ok(created);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }

    [HttpPatch("{id:int}")]
    public async Task<ActionResult> CompleteOrder([FromQuery] int id)
    {
        try
        {
            await orderLogic.CompleteOrderAsync(id);
            return Ok();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }

    [HttpDelete("{id:int}")]
    public async Task<IActionResult> DeleteAsync([FromQuery] int id)
    {
        string username = LoggedInUsername;
        try
        {
            await orderLogic.DeleteOrderAsync(id, username);
            return Ok();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
}