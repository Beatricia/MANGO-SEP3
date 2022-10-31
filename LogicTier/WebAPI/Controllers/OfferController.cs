using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Microsoft.AspNetCore.Mvc;

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

    [HttpGet]
    public async Task<ActionResult<IEnumerable<Offer>>> GetAsync()
    {
        try
        {
            var offers = await offerLogic.GetAsync();
            return Ok(offers);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
}