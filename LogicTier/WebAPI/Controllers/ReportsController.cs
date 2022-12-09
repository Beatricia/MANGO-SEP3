using Application.LogicInterfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using WebAPI.Controllers.Base;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class ReportController : LocallyController
{
    private readonly IReportLogic reportLogic;

    public ReportController(IReportLogic reportLogic)
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

    [Authorize(Roles = "admin")]
    [HttpDelete("{id}")]
    public async Task<ActionResult> DeleteAsync([FromRoute] long id)
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

}