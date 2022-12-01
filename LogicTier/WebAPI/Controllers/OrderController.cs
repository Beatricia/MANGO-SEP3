using Application.LogicInterfaces;
using Grpc.Core;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Shared.Models;
using WebAPI.Controllers.Base;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
//[Authorize]
public class OrderController : LocallyController
{
    private readonly IOrderLogic orderLogic;

    public OrderController(IOrderLogic orderLogic)
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
    public async Task<IActionResult> GetAsync(string username)
    {
        //string username = LoggedInUsername;
        try
        {
            var created = await orderLogic.GetAllOrders(username);
            
            return Created($"/orders", created);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }

    [HttpPatch]
    public async Task<ActionResult> CompleteOrder([FromBody]int id)
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

    [HttpDelete]
    public async Task<IActionResult> DeleteAsync(int id)
    {
        try
        {
            await orderLogic.DeleteOrderAsync(id);
            return Ok();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
}