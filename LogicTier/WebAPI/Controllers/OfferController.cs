using Application.LogicInterfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Shared.DTOs;
using Shared.Models;
using WebAPI.Utils;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
//[Authorize(Roles = "farmer")]
public class OfferController : ControllerBase
{
    private readonly IOfferLogic offerLogic;
    private ImageResource imageResource;

    public OfferController(IOfferLogic offerLogic, ImageResource resource)
    {
        this.offerLogic = offerLogic;
        imageResource = resource;
    }
    
    /// <summary>
    /// The method asynchronously creates a Offer object. If the object is correctly
    /// created them it is returned, and if not the exception is caught and a status code
    /// is sent to the user. 
    /// </summary>
    /// <param name="dto">A OfferCreationDto containing all information Required to create a Offer object</param>
    /// <returns>Returns the Offer object or a status code to indicate an error</returns>
    [HttpPost]
    public async Task<IActionResult> CreateAsync(OfferCreationDto dto)
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

    [HttpPost("{id:int}/image")]
    public async Task<IActionResult> UploadImage([FromRoute] int id)
    {
        if (!Request.HasFormContentType)
            return BadRequest("Not a form content type");

        if (Request.Form.Files.Count == 0)
            return BadRequest("No files in form");

        // TODO: check if the current user owns this offer


        string folder = "wwwroot/images/offers";
        Directory.CreateDirectory(folder);

        var file = Request.Form.Files[0];
        Image image = imageResource.GetImageForOffer(id);

        await imageResource.SaveImageAsync(imageResource.OfferImages, image.RelativeUrl, file);


        // check if the file is an image
        await imageResource.CheckImageAsync(imageResource.OfferImages, image.RelativeUrl);

        return Ok();
    }

    [HttpGet, AllowAnonymous]
    public async Task<IActionResult> GetAsync()
    {
        try
        {
            var created = await offerLogic.GetAsync();
            return Created($"/offers", created);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
    
    [HttpGet("farmName"), AllowAnonymous]
    public async Task<IActionResult> GetByFarmNameAsync([FromQuery] string farmName)
    {
        try
        {
            var created = await offerLogic.GetByFarmNameAsync(farmName);
            return Created($"/offers/{farmName}", created);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
}