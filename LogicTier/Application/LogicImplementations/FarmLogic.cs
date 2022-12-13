using System.Collections.ObjectModel;
using Application.DAOInterfaces;
using Application.LogicInterfaces;
using Shared.DTOs;
using Shared.Models;
using Farm = Shared.Models.Farm;

namespace Application.LogicImplementations;
public class FarmLogic : IFarmLogic
{
    private IFarmIconDao farmIconDao;
    private IFarmDao farmDao;
    private INotificationLogic notificationLogic;
    private IAddressDao addressDao;
    private IOfferLogic offerLogic;

    public FarmLogic(IFarmDao farmDao, IFarmIconDao farmIconDao, INotificationLogic notificationLogic, IAddressDao addressDao, IOfferLogic offerLogic)
    {
        this.farmIconDao = farmIconDao;
        this.farmDao = farmDao;
        this.notificationLogic = notificationLogic;
        this.addressDao = addressDao;
        this.offerLogic = offerLogic;
    }
    
    /// <summary>
    /// Create asynchronously a Farm using the FarmCreationDto object. Call the CreateAsync method in
    /// the FarmDao class and return the created Farm object as a Task.
    /// </summary>
    /// <param name="dto">The object holding all the farm information</param>
    /// <returns>The created Farm object</returns>
    public async Task<Farm> CreateAsync(FarmCreationDto dto)
    {
        ValidateData(dto);

        var existingFarm = await farmDao.GetFarmByNameAsync(dto.Name);
        if (existingFarm is not null)
            throw new Exception("Farm already exists.");
        
        

        // assign the default icon if the user didn't specify one
        FarmIcon icon;
        if (!farmIconDao.isValidIcon(dto.FarmIconFileName))
            icon = farmIconDao.DefaultIcon;
        else
            icon = farmIconDao.CreateIcon(dto.FarmIconFileName!);

        var address = dto.Address;
        double latitude;
        double longitude;

        try
        {
            (latitude, longitude) = await addressDao.GetCoordinatesAsync(address);
        }
        catch (ArgumentException e)
        {
            throw new ArgumentException("The address is invalid", e);
        }
        
        
        address.Latitude = latitude;
        address.Longitude = longitude;
        
        Farm farmToSend = new Farm
        {
            Name = dto.Name,
            Phone = dto.Phone,
            DeliveryDistance = dto.DeliveryDistance,
            FarmStatus = dto.FarmStatus,
            Address = dto.Address,
            Farmer = dto.Farmer, 
            FarmIcon = icon
        };

        await farmDao.CreateAsync(farmToSend);
        
        return farmToSend;
    }

    public async Task<Farm> GetFarmByNameAsync(string farmName)
    {
        return await farmDao.GetFarmByNameAsync(farmName);
    }
    
    public async Task<ICollection<Farm>> GetAllFarmsByFarmer(string username)
    {
        ICollection<Farm> farms = await farmDao.GetAllFarmsByFarmer(username);

        if (farms == null)
        {
            throw new Exception($"The farmer has no farms");
        }

        return farms;
    }

    public async Task UpdateFarmAsync(FarmUpdateDto dto)
    { 
        await farmDao.UpdateFarmAsync(dto);

        //send notification to customers who have an uncompleted order from the farm
        ICollection<String> usernames = await farmDao.GetAllCustomersUncompletedOrder(dto.Name);
        foreach (var username in usernames)
        {
            var notification = new NotificationCreationDto
            {
                Username = username,
                Text = $"{dto.Name} status changed: {dto.FarmStatus}" 
            };
            await notificationLogic.AddNotificationAsync(notification);
        }
    
    }

    public async Task<ICollection<Farm>> GetAllAsync()
    {
        return await farmDao.GetAllAsync();
    }

    public async Task<ICollection<Farm>> GetAllByNameAsync(string nameContains)
    {
        return await farmDao.GetAllByNameAsync(nameContains);
    }

    /// <summary>
    /// Disables a farm by setting its status to disabled and also called method in offerLogic to disable all offers from that farm
    /// </summary>
    /// <param name="farmName">Name of the farmer to be disabled</param>
    /// <returns></returns>
    public async Task DisableAsync(string farmName)
    {
        try
        {
            //disables farm
            await farmDao.DisableAsync(farmName);
            
            //disables all offers from that farm
            IEnumerable<Offer> farmOffers = await offerLogic.GetByFarmNameAsync(farmName);
            foreach (var offer in farmOffers)
            {
                await offerLogic.DisableAsync(offer.Id);
            }
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }


    /// <inheritdoc/>
    public ICollection<FarmIcon> GetAllIcons() => farmIconDao.AllIcons;

    private void ValidateData(FarmCreationDto dto)
    {
        //todo should we check this in here or with the rest of the check from the db fx. city...
        string name = dto.Name;
        string phone = dto.Phone;

        if (name.Length < 1)
        {
            throw new Exception("Farm name must be longer!");
        }

        if (name.Length > 100)
        {
            throw new Exception("Farm name is too long!");
        }

        if (phone.Length != 8)
        {
            throw new Exception("Invalid phone number!");
        }
    }

}
