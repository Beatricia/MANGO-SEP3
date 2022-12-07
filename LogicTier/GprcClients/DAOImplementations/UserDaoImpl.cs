using Application.DAOInterfaces;
using Shared.DTOs;

namespace GprcClients.DAOImplementations;

public class UserDaoImpl : IUserDao
{
    private UserService.UserServiceClient client;

    public UserDaoImpl(UserService.UserServiceClient client)
    {
        this.client = client;
    }

    public async Task<Shared.Models.Customer?> GetCustomer(string username)
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

    public async Task<Shared.Models.Farmer?> GetFarmer(string username)
    {
        var text = new Text
            { Text_ = username };

        try
        {
            Farmer farmer = await client.GetFarmerAsync(text);
            return ConvertFromGrpc(farmer);
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
            Customer grpcCustomer = ConvertToGrpc(customer);
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
            Farmer grpcFarmer = ConvertToGrpc(farmer);
            grpcFarmer = await client.RegisterFarmerAsync(grpcFarmer);
            return ConvertFromGrpc(grpcFarmer);
        }
        catch
        {
            return null;
        }
    }

    public async Task UpdateCustomerAsync(CustomerUpdateDto dto,string username)
    {
        var customerUpdate = new CustomerUpdate
        {
            Username = username
        };
        if (!string.IsNullOrEmpty(dto.Phone))
        {
            customerUpdate.Phone = dto.Phone;
        }
        if (!string.IsNullOrEmpty(dto.City))
        {
            customerUpdate.City = dto.City;
        }
        if (!string.IsNullOrEmpty(dto.Street))
        {
            customerUpdate.Street = dto.Street;
        }
        if (!string.IsNullOrEmpty(dto.Zip))
        {
            customerUpdate.Zip = dto.Zip;
        }
        
        customerUpdate.Latitude = dto.Latitude ?? double.MinValue;
        customerUpdate.Longitude = dto.Longitude ?? double.MinValue;
        
        try
        {
            await client.UpdateCustomerAsync(customerUpdate);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
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
                Zip = customer.Address.ZIP,
                Latitude = customer.Address.Latitude,
                Longitude = customer.Address.Longitude,
            }
        };
    }

    private Shared.Models.Customer ConvertFromGrpc(global::Customer customer)
    {
        Shared.Models.Address address = ConvertFromGrpc(customer.Address);
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

    private Shared.Models.Address ConvertFromGrpc(global::Address customerAddress)
    {
        var address = new Shared.Models.Address
        {
            City = customerAddress.City,
            Street = customerAddress.Street,
            ZIP = customerAddress.Zip,
            Latitude = customerAddress.Latitude,
            Longitude = customerAddress.Longitude,
        };
        return address;
    }
    
    
    private Farmer ConvertToGrpc(Shared.Models.Farmer farmer)
    {
        return new Farmer
        {
            Username = farmer.Username,
            Firstname = farmer.FirstName,
            Lastname = farmer.LastName,
        };
    }
    
    private Shared.Models.Farmer ConvertFromGrpc(Farmer farmer)
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