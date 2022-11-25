﻿using Application.LogicInterfaces;
using Microsoft.AspNetCore.Mvc;
using Shared.Models;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class CartItemController : ControllerBase
{
    private readonly ICartLogic cartLogic;

    public CartItemController(ICartLogic cartLogic)
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
    public async Task<ActionResult> DeleteAsync([FromQuery] string username)
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
}