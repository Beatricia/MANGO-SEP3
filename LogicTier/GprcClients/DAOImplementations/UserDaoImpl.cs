using Application.DAOInterfaces;
using Shared.Models;
using Address = Shared.Models.Address;
using Customer = Shared.Models.Customer;

namespace GprcClients.DAOImplementations;

public class UserDaoImpl : IUserDao
{
    private UserService.UserServiceClient client;

    public UserDaoImpl(UserService.UserServiceClient client)
    {
        this.client = client;
    }

    public async Task<Shared.Models.Customer> GetCustomer(string username)
    {
        var text = new Text
            { Text_ = username };

        try
        {
            global::Customer customer = await client.GetCustomerAsync(text);
            return ConvertFromGrpc(customer);
        }
        catch
        {
            return null;
        }
    }

    private Shared.Models.Customer ConvertFromGrpc(global::Customer customer)
    {
        Shared.Models.Address address = convertAddressFromGrpc(customer.Address);
        var customerToReturn = new Shared.Models.Customer
        {
            Username = customer.Username,
            Address = address,
            FirstName = customer.Firstname,
            LastName = customer.Lastname,
            Phone = customer.Phone
        };
        return customerToReturn;
    }

    private Shared.Models.Address convertAddressFromGrpc(global::Address customerAddress)
    {
        var address = new Shared.Models.Address
        {
            City = customerAddress.City,
            Street = customerAddress.Street,
            ZIP = customerAddress.Zip
        };
        return address;
    }
}