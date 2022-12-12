using Application.LogicInterfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Shared.DTOs;
using Shared.Models;
using WebAPI.Controllers.Base;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
[Authorize(Roles = "customer")]
public class CartsController : LocallyController
{
    private readonly ICartLogic cartLogic;

    public CartsController(ICartLogic cartLogic)
    {
        this.cartLogic = cartLogic;
    }

    [HttpPost]
    public async Task<IActionResult> CreateAsync([FromBody]CartOfferDto dto)
    {
        try
        {
            dto.Username = LoggedInUsername!;
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
    public async Task<IActionResult> GetAsync()
    {
        string username = LoggedInUsername!;
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
    public async Task<ActionResult> DeleteCartOffersAsync()
    {
        try
        {
            await cartLogic.DeleteAllCartOffersAsync(LoggedInUsername!);
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

    [HttpPatch]
    public async Task<ActionResult> Update([FromBody]UpdateCartOfferDto dto)
    {
        try
        {
            await cartLogic.UpdateAsync(dto);
            return Ok();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return BadRequest(e.Message);
        }
    }
}