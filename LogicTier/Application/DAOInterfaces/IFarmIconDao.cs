using Shared.Models;

namespace Application.DAOInterfaces;

/// <summary>
/// Access to the available icons to the farm
/// </summary>
public interface IFarmIconDao
{
    /// <summary>
    /// Gets all the available icons
    /// </summary>
    public ICollection<FarmIcon> AllIcons { get; }

    /// <summary>
    /// Gets the default icon
    /// </summary>
    public FarmIcon DefaultIcon { get; }

    /// <summary>
    /// Creates an icon object from the file name
    /// </summary>
    /// <param name="icon"></param>
    /// <returns></returns>
    public FarmIcon CreateIcon(string icon);

    /// <summary>
    /// Gets if the icon is available or not
    /// </summary>
    /// <param name="icon"></param>
    /// <returns></returns>
    public bool isValidIcon(string? icon);
}