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

    public FarmLogic(IFarmDao farmDao, IFarmIconDao farmIconDao)
    {
        this.farmIconDao = farmIconDao;
        this.farmDao = farmDao;
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

        // assign the default icon if the user didn't specify one
        FarmIcon icon;
        if (!farmIconDao.isValidIcon(dto.FarmIconFileName))
            icon = farmIconDao.DefaultIcon;
        else
            icon = farmIconDao.CreateIcon(dto.FarmIconFileName!);
        
        
        Farm farmToSend = new Farm
        {
            Name = dto.Name,
            Phone = dto.Phone,
            DeliveryDistance = dto.DeliveryDistance,
            FarmStatus = dto.FarmStatus,
            Address = dto.Address,
            Farmer = new Farmer()
            {
                Username = dto.FarmerUsername,
                FirstName = "",
                LastName = "",
            },
            FarmIcon = icon
        };

        await farmDao.CreateAsync(farmToSend);
        
        return farmToSend;
    }

    public async Task<Farm> GetFarmByNameAsync(string farmName)
    {
        return await farmDao.GetFarmByNameAsync(farmName);
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