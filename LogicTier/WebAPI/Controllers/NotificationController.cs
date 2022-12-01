using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Shared.DTOs;
using WebAPI.Controllers.Base;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class NotificationController : LocallyController
{
    private INotificationLogic dao;

    public NotificationController(INotificationLogic dao)
    {
        this.dao = dao;
    }
    
    /// <summary>
    /// Gets all the notifications for the currently logged in user
    /// </summary>
    /// <returns></returns>
    [HttpGet]
    //[Authorize("MustBeFarmer")]
    //[Authorize("MustBeCustomer")]
    public async Task<IActionResult> Get()
    {
        var nots = await dao.GetNotificationsAsync(LoggedInUsername!);
        return Ok(nots);
    }

    /// <summary>
    /// Gets all the notifications for a user
    /// </summary>
    /// <param name="username"></param>
    /// <returns></returns>
    [HttpGet("{username:regex([[\\w\\W]]+)}")]
    public async Task<IActionResult> GetForUser([FromRoute] string username)
    {
        var nots = await dao.GetNotificationsAsync(username);
        return Ok(nots);
    }

    /// <summary>
    /// Creates a notification for a user
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