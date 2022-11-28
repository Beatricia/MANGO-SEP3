using Application.DAOInterfaces;

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
    
    public async Task<Shared.Models.Customer> RegisterCustomer(Shared.Models.Customer customer)
    {
        try
        {
            global::Customer grpcCustomer = ConvertToGrpc(customer);
            grpcCustomer = await client.RegisterCustomerAsync(grpcCustomer);
            return ConvertFromGrpc(grpcCustomer);
        }
        catch
        {
            return null;
        }
    }
    
    // register farmer
    public async Task<Shared.Models.Farmer> RegisterFarmer(Shared.Models.Farmer farmer)
    {
        try
        {
            global::Farmer grpcFarmer = ConvertToGrpc(farmer);
            grpcFarmer = await client.RegisterFarmerAsync(grpcFarmer);
            return ConvertFromGrpc(grpcFarmer);
        }
        catch
        {
            return null;
        }
    }

    private global::Customer ConvertToGrpc(Shared.Models.Customer customer)
    {
        return new global::Customer
        {
            Username = customer.Username,
            Firstname = customer.FirstName,
            Lastname = customer.LastName,
            Phone = customer.Phone,
            Address = new global::Address
            {
                Street = customer.Address.Street,
                City = customer.Address.City,
                Zip = customer.Address.ZIP
            }
        };
    }

    private Shared.Models.Customer ConvertFromGrpc(global::Customer customer)
    {
        Shared.Models.Address address = convertFromGrpc(customer.Address);
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

    private Shared.Models.Address convertFromGrpc(global::Address customerAddress)
    {
        var address = new Shared.Models.Address
        {
            City = customerAddress.City,
            Street = customerAddress.Street,
            ZIP = customerAddress.Zip
        };
        return address;
    }
    
    
    private global::Farmer ConvertToGrpc(Shared.Models.Farmer farmer)
    {
        return new global::Farmer
        {
            Username = farmer.Username,
            Firstname = farmer.FirstName,
            Lastname = farmer.LastName,
        };
    }
    
    private Shared.Models.Farmer ConvertFromGrpc(global::Farmer farmer)
    {
        var farmerToReturn = new Shared.Models.Farmer
        {
            Username = farmer.Username,
            FirstName = farmer.Firstname,
            LastName = farmer.Lastname,
        };
        return farmerToReturn;
    }
}