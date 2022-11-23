namespace WebAPI.Utils;

public class FarmIconResource
{
    // TODO: mention the source of the icons in the docs somewhere: https://www.flaticon.com/free-icons/farm
    
    public const string BaseFolder = "wwwroot/images/farmicons/";

    public ICollection<string> AllIcons
    {
        get
        {
            Directory.CreateDirectory(BaseFolder);
        
            // get all farm icons from wwwroot/images/farmicons
            ICollection<string> icons = Directory.GetFiles(BaseFolder, "*.png", SearchOption.TopDirectoryOnly)
                .Select(Path.GetFileName)
                .Where(path => path is not null)
                .Cast<string>()
                .Select(path => Path.Combine(BaseFolder, path))
                .ToList();

            return icons;
        }
    }
    
    public string DefaultIcon => Path.Combine(BaseFolder, "default.png");
}