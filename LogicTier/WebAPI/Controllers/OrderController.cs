using Application.LogicInterfaces;
using Grpc.Core;
using Microsoft.AspNetCore.Mvc;
using Shared.Models;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class OrderController : ControllerBase
{
    private readonly IOrderLogic orderLogic;

    public OrderController(IOrderLogic orderLogic)
    {
        this.orderLogic = orderLogic;
    }
    
    [HttpPost]
    public async Task<IActionResult> CreateAsync(string username)
    {
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
    public async Task<IActionResult> GetAsync([FromQuery]string username)
    {
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
}