using Application.LogicInterfaces;
using Microsoft.AspNetCore.Mvc;
using Shared.DTOs;
using Shared.Models;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class FarmController : ControllerBase
{
    private readonly IFarmLogic farmLogic;

    public FarmController(IFarmLogic farmLogic)
    {
        this.farmLogic = farmLogic;
    }
    
    /// <summary>
    /// The method asynchronously creates a Farm object. If the object is correctly
    /// created them it is returned, and if not the exception is caught and a status code
    /// is sent to the user. 
    /// </summary>
    /// <param name="dto">A FarmCreationDto containing all information Required to create a Farm object</param>
    /// <returns>Returns the farm object or a status code to indicate an error</returns>
    [HttpPost]
    public async Task<IActionResult> CreateAsync(FarmCreationDto dto)
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
    
    [HttpGet]
    public async Task<IActionResult> GetAsync([FromQuery]string farmName)
    {
        try
        {
            var farm = await farmLogic.GetFarmByNameAsync(farmName);
            return Created($"/Farms", farm);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
    
}