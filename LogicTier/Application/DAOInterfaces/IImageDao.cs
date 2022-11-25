using Shared.Models;

namespace Application.DAOInterfaces;

public interface IImageDao
{
    public string OfferImages { get; }

    /// <summary>
    /// Gets the absolute url of the image.
    /// </summary>
    /// <param name="path"></param>
    /// <returns></returns>
    public string GetAbsoluteUrl(string path);

    /// <summary>
    /// Creates the relative path to the image for an offer
    /// </summary>
    /// <param name="offerId"></param>
    /// <returns></returns>
    public Image CreateRelativePathOffer(int offerId);
}