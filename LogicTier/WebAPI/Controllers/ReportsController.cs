using Application.LogicInterfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Shared.DTOs;
using WebAPI.Controllers.Base;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class ReportsController : LocallyController
{
    private readonly IReportLogic reportLogic;

    public ReportsController(IReportLogic reportLogic)
    {
        this.reportLogic = reportLogic;
    }

    [Authorize(Roles = "admin")]
    [HttpGet]
    public async Task<IActionResult> GetAsync()
    {
        try
        {
            var created = await reportLogic.GetAllReports();
            return Ok(created);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }

    [Authorize(Roles = "customer")]
    [HttpPost]
    public async Task<IActionResult> CreateReportAsync([FromBody] ReportCreationDto dto)
    {
        dto.ReportedByCustomerUsername = LoggedInUsername!;
        try
        {
            var report = await reportLogic.ReportOfferAsync(dto);
            return Created("", report);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return BadRequest(e.Message);
        }
    }

    [Authorize(Roles = "admin")]
    [HttpDelete("{id}")]
    public async Task<ActionResult> DeleteAsync([FromQuery] long id)
    {
        try
        {
            await reportLogic.DeleteReportAsync(id);
            return Ok();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
    
    [Authorize(Roles = "admin")]
    [HttpPost("{id}")]
    public async Task<ActionResult> PostAsync([FromQuery] long id)
    {
        try
        {
            await reportLogic.NotifyFarmerAsync(id);
            return Ok();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }

}