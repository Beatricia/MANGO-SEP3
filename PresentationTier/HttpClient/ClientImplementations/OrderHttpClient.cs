using System.Net.Http.Json;
using System.Runtime.InteropServices;
using System.Text.Json;
using HttpClient.ClientInterfaces;
using Shared.Models;

namespace HttpClient.ClientImplementations;

public class OrderHttpClient : IOrderService
{
    private System.Net.Http.HttpClient Client => apiAccess.HttpClient;
    private readonly ApiAccess apiAccess;
    public OrderHttpClient(ApiAccess apiAccess)
    {
        this.apiAccess = apiAccess;
    } 
    
    public async Task CreateOrderAsync()
    {
        HttpResponseMessage response = await Client.PostAsJsonAsync("/order",string.Empty);
        if (!response.IsSuccessStatusCode)
        {
            string content = await response.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
    }

    public async Task<ICollection<Order>> GetAllOrdersAsync()
    {
        
        HttpResponseMessage response = await Client.GetAsync("/order");
        string content = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        ICollection<Order> orders = JsonSerializer.Deserialize<ICollection<Order>>(content, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;
        /*
        Offer offer1 = new Offer
            {Id = 1,
                Name = "tomato",
                Delivery = true,
                FarmName = "Happy Farm",
                PickYourOwn = true,
                PickUp = false,
                Quantity = 3,
                Unit = "kg",
                Price = 20.5
            };
        Offer offer2 = new Offer
        {Id = 1,
            Name = "potato",
            Delivery = true,
            FarmName = "Happy Farm",
            PickYourOwn = true,
            PickUp = false,
            Quantity = 3,
            Unit = "kg",
            Price = 20.5
        };
        Offer offer3 = new Offer
        {Id = 1,
            Name = "milk",
            Delivery = true,
            FarmName = "Happy Farm",
            PickYourOwn = true,
            PickUp = false,
            Quantity = 3,
            Unit = "kg",
            Price = 20.5
        };
       
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
        Order[] orders = { order1, order2, order3, order4 };*/
        return orders;
    }

    public async Task DeleteOrderAsync(int id)
    {
        HttpResponseMessage response = await Client.DeleteAsync($"/order/{id}");
        if (!response.IsSuccessStatusCode)
        {
            string content = await response.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
    }
}