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

public class FarmsController : LocallyController
{
    private readonly IFarmLogic farmLogic;
    private readonly IReviewLogic reviewLogic;

    public FarmsController(IFarmLogic farmLogic, IReviewLogic reviewLogic)
    {
        this.farmLogic = farmLogic;
        this.reviewLogic = reviewLogic;
    }
    
    /// <summary>
    /// The method asynchronously creates a Farm object. If the object is correctly
    /// created them it is returned, and if not the exception is caught and a status code
    /// is sent to the user. 
    /// </summary>
    /// <param name="dto">A FarmCreationDto containing all information Required to create a Farm object</param>
    /// <returns>Returns the farm object or a status code to indicate an error</returns>
    [HttpPost, Authorize(Roles = "farmer")]
    public async Task<IActionResult> CreateAsync([FromBody]FarmCreationDto dto)
    {
        try
        {
            Shared.Models.Farm created = await farmLogic.CreateAsync(dto);
            return Created($"/farms/{created.Name}", created);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }


    /// <summary>
    /// Gets all the available farm icons
    /// </summary>
    /// <returns></returns>
    [HttpGet("icons")]
    public IActionResult GetAllIcons()
    {
        return Ok(farmLogic.GetAllIcons());
    }
    
    [HttpGet]
    [Authorize]
    public async Task<IActionResult> GetAsync([FromQuery]string farmName)
    {
        try
        {
            var farms = await farmLogic.GetAllByNameAsync(farmName);
            return Ok(farms);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
    
        
    //change me to something more appropriate
    [HttpGet("myFarms")]
    [Authorize(Roles = "farmer")]
    public async Task<IActionResult> GetAllFarmsByFarmer()
    {
        try
        {
            var created = await farmLogic.GetAllFarmsByFarmer(LoggedInUsername!);
            return Created($"/farms", created);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        } 
    }
    
    
    [HttpPatch]
    public async Task<ActionResult> UpdateAsync([FromBody]FarmUpdateDto dto)
    {
        try
        {
            await farmLogic.UpdateFarmAsync(dto);
            return Ok();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return BadRequest(e.Message);
        }
    }
    
    [HttpGet("all")]
    [Authorize]
    public async Task<IActionResult> GetAllFarms()
    {
        try
        {
            var created = await farmLogic.GetAllAsync();
            return Created($"/farms", created);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
    
    [HttpGet("{nameContains}")]
    [Authorize]
    public async Task<IActionResult> GetFarmByName([FromRoute]string nameContains)
    {
        try
        {
            var farms = await farmLogic.GetAllByNameAsync(nameContains);
            
            if(farms.Any())
                return Ok(farms.First());
            
            return NotFound();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
    
    /// <summary>
    /// The method asynchronously disables a Farm object.
    /// </summary>
    /// <param name="farmName">Name of the farm to be disabled</param>
    /// <returns>Returns Action result e.g. Ok if request was successfully completed</returns>
    [HttpPatch("{farmName}/disabled")]
    [Authorize]
    public async Task<ActionResult> DisableAsync([FromRoute] string farmName)
    {
        try
        {
            await farmLogic.DisableAsync(farmName);
            return Ok();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }

    [HttpPost("{name:regex([[\\w\\W]]+)}/reviews")]
    [Authorize(Roles = "customer")]
    public async Task<IActionResult> PostReview([FromRoute]string name, [FromBody]ReviewCreationDto dto)
    {
        try
        {
            var review = await reviewLogic.CreateReviewAsync(name, LoggedInUsername!, dto);
            return Ok(review);
        }
        catch (Exception e)
        {
            return BadRequest(e.Message);
        }
    }
    
    [HttpGet("{farmName}/reviews")]
    public async Task<IActionResult> GetReviews([FromRoute]string farmName)
    {
        try
        {
            var reviews = await reviewLogic.GetAllReviewsByFarmAsync(farmName);
            return Ok(reviews);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return BadRequest(e.Message);
        }
    }
    
    [HttpPatch("{name:regex([[\\w\\W]]+)}/reviews/{id:long}")]
    [Authorize(Roles = "customer")]
    public async Task<IActionResult> UpdateReview([FromRoute] long id, [FromBody] UpdateReviewDto review)
    {
        review.Username = LoggedInUsername!;
        review.Id = id;
        var reviewEdited = await reviewLogic.EditReviewAsync(review);
        return Ok(reviewEdited);
    }
}