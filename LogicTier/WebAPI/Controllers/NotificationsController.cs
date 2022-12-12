using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Shared.DTOs;
using WebAPI.Controllers.Base;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class NotificationsController : LocallyController
{
    private INotificationLogic dao;

    public NotificationsController(INotificationLogic dao)
    {
        this.dao = dao;
    }
    
    /// <summary>
    /// Gets all the notifications for the currently logged in user
    /// </summary>
    /// <returns></returns>
    [HttpGet]
    [Authorize("FarmerOrCustomer")]
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
    
    [HttpDelete]
    public async Task<IActionResult> Delete([FromBody] Shared.Models.Notification notification)
    {
        notification.ToUsername = LoggedInUsername!;
        
        await dao.DeleteNotificationAsync(notification);
        return Ok();
    }
}