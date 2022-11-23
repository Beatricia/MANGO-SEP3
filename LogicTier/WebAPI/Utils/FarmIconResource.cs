using Application.DAOInterfaces;

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


    private ICollection<string>? icons;

    //   if the 'icons' is not null, returns the 'icons', if it is null, then reads all the icons from the folder,
    //   assigns them to the 'icons' and returns them
    /// <inheritdoc/>
    public ICollection<string> AllIcons => icons ??= ReadAllIcons();
    /// <inheritdoc/>
    public string DefaultIcon => Path.Combine(DisplayFolder, "default.png");
    
    
    /// <inheritdoc/>
    public bool isValidIcon(string? icon)
    {
        // check if icon is null
        if (icon is null)
            return false;
        
        // check if icon is in the AllIcons
        return AllIcons.Contains(icon);
    }


    private ICollection<string> ReadAllIcons()
    {
        Directory.CreateDirectory(BaseFolder);
        
        // get all farm icons from wwwroot/images/farmicons
        ICollection<string> icons = Directory.GetFiles(BaseFolder, "*.png", SearchOption.TopDirectoryOnly)
            .Select(Path.GetFileName)
            .Where(path => path is not null)
            .Cast<string>()
            .Select(path => Path.Combine(DisplayFolder, path))
            .ToList();

        return icons;
    }
}