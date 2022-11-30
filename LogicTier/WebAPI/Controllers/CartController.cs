using Application.LogicInterfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Shared.Models;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
[Authorize(Roles = "customer")]
public class CartController : ControllerBase
{
    private readonly ICartLogic cartLogic;

    public CartController(ICartLogic cartLogic)
    {
        this.cartLogic = cartLogic;
    }

    [HttpPost]
    public async Task<IActionResult> CreateAsync(CartOfferDto dto)
    {
        try
        {
            await cartLogic.AddToCartAsync(dto);
            return Ok();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }

    [HttpGet]
    public async Task<IActionResult> GetAsync([FromQuery] string username)
    {
        try
        {
            var created = await cartLogic.GetAllCartItemsAsync(username);
            return Created($"/cartOffers", created);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }

    [HttpDelete]
    public async Task<ActionResult> DeleteCartOffersAsync([FromQuery] string username)
    {
        try
        {
            await cartLogic.DeleteAllCartOffersAsync(username);
            return Ok();
        }
        catch(Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }

    [HttpDelete("{id:int}")]
    public async Task<ActionResult> DeleteCartOfferAsync([FromRoute] int id)
    {
        try
        {
            await cartLogic.DeleteCartOfferAsync(id);
            return Ok();
        }
        catch(Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
    
    [HttpGet("{id:int}")]
    public async Task<IActionResult> GetCartOfferById([FromRoute] int id)
    {
        try
        {
            Shared.Models.CartOffer result = await cartLogic.GetCartOfferById(id);
            return Ok(result);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
}