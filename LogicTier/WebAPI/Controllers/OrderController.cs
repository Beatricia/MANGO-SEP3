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
    public async Task<IActionResult> CreateAsync([FromBody]string username)
    {
        //string? username = LoggedInUsername;
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
        //string? username = LoggedInUsername;
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