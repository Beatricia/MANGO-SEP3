using System.Net.Http.Json;
using System.Text.Json;
using HttpClient.ClientInterfaces;
using Shared.Models;

namespace HttpClient.ClientImplementations;

public class OrderHttpClient : IOrderService
{
    private readonly System.Net.Http.HttpClient client;

    public OrderHttpClient(System.Net.Http.HttpClient client)
    {
        this.client = client;
    } 
    
    public async Task CreateOrderAsync(string username)
    {
        HttpResponseMessage response = await client.PostAsJsonAsync("/order", username);
        if (!response.IsSuccessStatusCode)
        {
            string content = await response.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
    }

    public async Task<ICollection<Order>> GetAllOrdersAsync(string username)
    {
        /*
        HttpResponseMessage response = await client.GetAsync($"/order?username={username}");
        string content = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        ICollection<Order> orders = JsonSerializer.Deserialize<ICollection<Order>>(content, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;*/
        Offer offer1 = new Offer(1, "tomato", 1, "kg", 20.5, true, true, true, null, "sth");
        Offer offer2 = new Offer(2, "onion", 1, "kg", 13.5, true, true, true, null, "sth");
        Offer offer3 = new Offer(3, "milk", 1, "kg", 18.75, true, true, true, null, "sth");
        OrderOffer orderItem1 = new OrderOffer
        {
            Id = 1,
            Offer = offer1,
            Quantity = 15,
            Username = "Agata",
            CollectionOption = "PickUp"
        };
        OrderOffer orderItem2 = new OrderOffer
        {
            Id = 1,
            Offer = offer1,
            Quantity = 3,
            Username = "Agata",
            CollectionOption = "PickUp"
        };
        OrderOffer orderItem3 = new OrderOffer
        {
            Id = 1,
            Offer = offer2,
            Quantity = 4,
            Username = "Agata",
            CollectionOption = "Delivery"
        };
        OrderOffer orderItem4 = new OrderOffer
        {
            Id = 1,
            Offer = offer3,
            Quantity = 2,
            Username = "Agata",
            CollectionOption = "Delivery"
        };
        OrderOffer orderItem5 = new OrderOffer
        {
            Id = 1,
            Offer = offer3,
            Quantity = 3,
            Username = "Agata",
            CollectionOption = "PickUp"
        };


        Order order1 = new Order
        {
            Id = 1,
            OrderOffers = new List<OrderOffer> { orderItem1, orderItem2, orderItem5 },
            IsDone = false,
            FarmName = "Happy Farm",
            CollectionOption = "PickUp"
        };
        Order order2 = new Order
        {
            Id = 1,
            OrderOffers = new List<OrderOffer> { orderItem3, orderItem1, orderItem5 },
            IsDone = false,
            FarmName = "Happy Farm",
            CollectionOption = "PickUp"
        };
        Order order3 = new Order
        {
            Id = 1,
            OrderOffers = new List<OrderOffer> {orderItem4, orderItem5 },
            IsDone = false,
            FarmName = "Happy Farm",
            CollectionOption = "Delivery"
        };
        Order order4 = new Order
        {
            Id = 1,
            OrderOffers = new List<OrderOffer> { orderItem1, orderItem2, orderItem5, orderItem3 },
            IsDone = false,
            FarmName = "Happy Farm",
            CollectionOption = "PickUp"
        };
        Order[] orders = { order1, order2, order3, order4 };
        return orders;
    }
}