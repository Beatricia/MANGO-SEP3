using Application.LogicInterfaces;
using Microsoft.AspNetCore.Mvc;
using Shared.DTOs;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]

public class OfferController : ControllerBase
{
    private readonly IOfferLogic offerLogic;

    public OfferController(IOfferLogic offerLogic)
    {
        this.offerLogic = offerLogic;
    }
    
    /// <summary>
    /// The method asynchronously creates a Offer object. If the object is correctly
    /// created them it is returned, and if not the exception is caught and a status code
    /// is sent to the user. 
    /// </summary>
    /// <param name="dto">A OfferCreationDto containing all information Required to create a Offer object</param>
    /// <returns>Returns the Offer object or a status code to indicate an error</returns>
    [HttpPost]
    public async Task<ActionResult<Shared.Models.Offer>> CreateAsync(OfferCreationDto dto)
    {
        try
        {
            Shared.Models.Offer created = await offerLogic.CreateAsync(dto);
            return Created($"/offers/{created.Id}", created);
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
        try
        {
            Console.WriteLine("REQUEST RECEIVED");
            var created = await offerLogic.GetAsync();
            return Created($"/offers", created);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
}