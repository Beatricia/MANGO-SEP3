using Application.LogicInterfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Shared.DTOs;
using Shared.Models;
using WebAPI.Controllers.Base;
using WebAPI.Utils;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]

public class OfferController : LocallyController
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
    [Authorize(Roles = "farmer")]
    [HttpPost, AllowAnonymous]
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
        FileInfo info = new FileInfo(image.RelativeUrl);

        await imageResource.SaveImageAsync(imageResource.OfferImages, info.Name, file);


        // check if the file is an image
        await imageResource.CheckImageAsync(imageResource.OfferImages, info.Name);

        return Ok();
    }

    [HttpGet, AllowAnonymous]
    public async Task<IActionResult> GetAsync([FromQuery] string? username, int? distance, string? nameContains, bool? delivery, bool? pickUp, bool? pickYo)
    {
        try
        {
            SearchOfferParameterDto dto = new SearchOfferParameterDto(username, distance, nameContains, delivery, pickUp, pickYo);
          
            var offers = await offerLogic.GetAsync(dto);
            return Ok(offers);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }

    [HttpGet("recommended")]
    [Authorize(Roles = "customer")]
    public async Task<IActionResult> GetRecommended()
    {
        var recommended = await offerLogic.GetRecommendedAsync(LoggedInUsername!);
        return Ok(recommended);
    }
    
    [HttpGet("{farmName}")]
    public async Task<IActionResult> GetAsync([FromRoute] string farmName)
    {
        // TODO: make this a rest endpoint
        try
        {
            var offers = await offerLogic.GetByFarmNameAsync(farmName);
            Console.WriteLine($"numnber of offers: {offers.Count()}");
            Console.WriteLine($"farm name: {farmName}");
            return Ok(offers);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }

    /// <summary>
    /// The method asynchronously disables an Offer object.
    /// </summary>
    /// <param name="id">An id of the offer to be disabled</param>
    /// <returns>Returns Action result e.g. Ok if request was completed</returns>
    [Authorize(Roles = "farmer")]
    [HttpPatch("{id:int}"), AllowAnonymous]
    public async Task<ActionResult> DisableAsync([FromRoute] int id)
    {
        try
        {
            await offerLogic.DisableAsync(id);
            return Ok();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }
}