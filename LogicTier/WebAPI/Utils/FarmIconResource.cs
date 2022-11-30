using Application.DAOInterfaces;
using Shared.Models;

namespace WebAPI.Utils;

/// <summary>
/// Access to all the downloaded icons in the application
/// </summary>
public class FarmIconResource : IFarmIconDao
{
    // TODO: mention the source of the icons in the docs somewhere: https://www.flaticon.com/free-icons/farm
    
    // this is the path to the folder where the icons are stored
    private const string BaseFolder = "wwwroot/images/farmicons/";
    
    // this is the route to the icon when you are using the api
    private const string DisplayFolder = "/images/farmicons/";

    // filename.extension, for example ["default.png", "happy-cow.png"]
    private static ICollection<string>? allIcons;


    private readonly HttpRequest request;
    public FarmIconResource(IHttpContextAccessor context)
    {
        request = context.HttpContext.Request; 
    }
    
    
    //   if the 'icons' is not null, returns the 'icons', if it is null, then reads all the icons from the folder,
    //   assigns them to the 'icons' and returns them
    /// <inheritdoc/>
    public ICollection<FarmIcon> AllIcons
    {
        get
        {
            allIcons ??= ReadAllIcons();

            ICollection<FarmIcon> icons = allIcons.Select(CreateIcon).ToList();

            return icons;
        }
    }

    /// <inheritdoc/>
    public FarmIcon DefaultIcon => CreateIcon("default.png");//Path.Combine(DisplayFolder, "default.png");
    
    
    /// <inheritdoc/>
    public bool isValidIcon(string? iconFile)
    {
        // check if icon is null
        if (iconFile is null)
            return false;
        
        return allIcons?.Contains(iconFile) ?? false;
    }
    
    
    /// <summary>
    /// Creates a farm icon object from the file name
    /// </summary>
    /// <param name="icon"></param>
    /// <returns></returns>
    public FarmIcon CreateIcon(string icon)
    {
        try
        {
            string iconName = icon[..icon.LastIndexOf(".", StringComparison.Ordinal)].Replace('-', ' ');

            return new()
            {
                IconName = iconName,
                FileName = icon,
                AbsoluteUrl = request.Scheme + "://" + request.Host + DisplayFolder + icon
            };
        }
        catch
        {
            return DefaultIcon;
        }
    }


    /// <summary>
    /// Returns all the png file names and extension from the BaseFolder.
    /// </summary>
    /// <returns></returns>
    private static ICollection<string> ReadAllIcons()
    {
        Directory.CreateDirectory(BaseFolder);
        
        // get all farm icons from wwwroot/images/farmicons
        ICollection<string> iconsTemp = Directory.GetFiles(BaseFolder, "*.png", SearchOption.TopDirectoryOnly)
            .Select(Path.GetFileName)
            .Where(path => path is not null)
            .Cast<string>()
            .ToList();

        return iconsTemp;
    }
}