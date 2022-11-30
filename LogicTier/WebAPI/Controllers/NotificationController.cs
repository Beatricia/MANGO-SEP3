using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Microsoft.AspNetCore.Mvc;
using Shared.DTOs;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class NotificationController : ControllerBase
{
    private INotificationLogic dao;

    public NotificationController(INotificationLogic dao)
    {
        this.dao = dao;
    }
    
    [HttpGet("{username:regex(^[[\\w\\W]])}")]
    public async Task<IActionResult> Get([FromRoute] string username)
    {
        var nots = await dao.GetNotificationsAsync(username);
        return Ok(nots);
    }

    /// <summary>
    /// This is only for test purpose
    /// </summary>
    /// <param name="username"></param>
    /// <param name="text"></param>
    /// <returns></returns>
    [HttpPost(Name = "CreateNotification")]
    public async Task<IActionResult> Create([FromBody] NotificationCreationDto dto)
    {
        
        await dao.AddNotificationAsync(dto);

        return Ok();
    }
}