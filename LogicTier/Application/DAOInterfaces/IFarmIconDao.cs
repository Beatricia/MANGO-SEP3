namespace Application.DAOInterfaces;

/// <summary>
/// Access to the available icons to the farm
/// </summary>
public interface IFarmIconDao
{
    /// <summary>
    /// Gets all the available icons
    /// </summary>
    public ICollection<string> AllIcons { get; }

    /// <summary>
    /// Gets the default icon
    /// </summary>
    public string DefaultIcon { get; }

    /// <summary>
    /// Gets if the icon is available or not
    /// </summary>
    /// <param name="icon"></param>
    /// <returns></returns>
    public bool isValidIcon(string? icon);
}