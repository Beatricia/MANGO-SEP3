using Application.LogicInterfaces;
using Grpc.Core;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class OrderController
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
            //return
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            //return StatusCode(e.Message);
        }
    }
    
    [HttpGet]
    public async Task<IActionResult> GetAsync(string username)
    {
        try
        {
            var created = await orderLogic.GetAllOrders(username);
            return created($"/orders", created);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
}