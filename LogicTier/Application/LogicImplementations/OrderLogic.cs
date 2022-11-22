using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Shared.Models;

namespace Application.LogicImplementations;

public class OrderLogic : IOrderLogic
{
    private readonly IOrderDao dao;

    public OrderLogic(IOrderDao dao)
    {
        this.dao = dao;
    }

    public Task CreateOrderAsync(string Username)
    {
        throw new NotImplementedException();
    }

    public Task<IEnumerable<Order>> GetAllOrders(string Username)
    {
        throw new NotImplementedException();
    }
}