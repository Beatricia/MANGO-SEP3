using System.Drawing;
using System.Drawing.Imaging;
using Application.DAOInterfaces;

namespace WebAPI.Utils;

public class ImageResource : IImageDao
{
    private const string BaseFolder = "wwwroot/";
    public string OfferImages => "images/offers/";

    private readonly IHttpContextAccessor httpContextAccessor;

    public ImageResource(IHttpContextAccessor httpContextAccessor)
    {
        this.httpContextAccessor = httpContextAccessor;
    }

    /// <summary>
    /// Saves an image to the specified folder and filename. If a file already exists with the same name, it will be overwritten.
    /// </summary>
    /// <param name="folder"></param>
    /// <param name="filename"></param>
    /// <param name="image"></param>
    public async Task SaveImageAsync(string folder, string filename, IFormFile image)
    {
        string path = Path.Join(BaseFolder, folder, filename);
        
        
        Directory.CreateDirectory(Path.GetDirectoryName(path)!);
        
        // saves an image as a png
        await using var fs = new FileStream(path, FileMode.Create);
        using Image bitmap = Bitmap.FromStream(image.OpenReadStream()); // please use windows, thanks
        await Task.Run(() => bitmap.Save(fs, ImageFormat.Png));// if you made it to this line, please use windows again, thanks
    }
    
    /// <summary>
    /// Throws exception if the file is not a png or a jpg
    /// </summary>
    /// <param name="folder"></param>
    /// <param name="filename"></param>
    /// <exception cref="Exception"></exception>
    public async Task CheckImageAsync(string folder, string filename)
    {
        string path = Path.Join(BaseFolder, folder, filename);
        
        await using var checkFs = new FileStream(path, FileMode.Open);
        
        var pngHeader = new byte[] {137, 80, 78, 71, 13, 10, 26, 10};
        var jpgHeader = new byte[] {255, 216, 255};

        for (int i = 0; i < pngHeader.Length; i++)
        {
            int num = checkFs.ReadByte();
            
            bool isJpg = i < jpgHeader.Length && num == jpgHeader[i];
            bool isPng = num == pngHeader[i];
            
            if (num == -1 || (!isJpg && !isPng))
            {
                checkFs.Close();
                File.Delete(path);
                throw new Exception("Only valid image format is jpg or png.");
            }
        }
    }

    

    /// <summary>
    /// Gets the absolute url of the image.
    /// </summary>
    /// <param name="path"></param>
    /// <returns></returns>
    public string GetAbsoluteUrl(string path)
    {
        return $"{httpContextAccessor.HttpContext.Request.Scheme}://{httpContextAccessor.HttpContext.Request.Host}/{path}";
    }

    public Shared.Models.Image CreateRelativePathOffer(int offerId)
    {
        string relativePath = Path.Join(OfferImages, $"{offerId}.png");
        var image = new Shared.Models.Image
        {
            RelativeUrl = relativePath,
            AbsoluteUrl = GetAbsoluteUrl(relativePath)
        };
        
        return image;
    }
}
