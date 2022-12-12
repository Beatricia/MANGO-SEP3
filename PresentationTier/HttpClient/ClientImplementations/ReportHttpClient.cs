using System.Net.Http.Json;
using System.Text;
using System.Text.Json;
using HttpClient.ClientInterfaces;
using Shared.DTOs;
using Shared.Models;

namespace HttpClient.ClientImplementations;

public class ReportHttpClient : IReportService
{
    private const string Endpoint = "/reports";
    
    private System.Net.Http.HttpClient client => _apiAccess.HttpClient;
    private ApiAccess _apiAccess;

    public ReportHttpClient(ApiAccess access)
    {
        _apiAccess = access;
    }
    
    public async Task<ICollection<Report>> GetAllReports()
    {
        HttpResponseMessage response = await client.GetAsync(Endpoint); 
        string content = await response.Content.ReadAsStringAsync();

        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        ICollection<Report> reports = JsonSerializer.Deserialize<ICollection<Report>>(content,
            new JsonSerializerOptions
            {
                PropertyNameCaseInsensitive = true
            })!;
        return reports;
    }

    public async Task IgnoreReportAsync(long id)
    {
        HttpResponseMessage response = await client.DeleteAsync($"{Endpoint}/{id}");
        if (!response.IsSuccessStatusCode)
        {
            string content = await response.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
    }

    public async Task NotifyFarmerAsync(long id)
    {
        HttpResponseMessage response = await client.PostAsync($"{Endpoint}/{id}", null);
        if (!response.IsSuccessStatusCode)
        {
            string content = await response.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
    }

    public async Task ReportOfferAsync(ReportCreationDto dto)
    {
        HttpResponseMessage responseMessage = await client.PostAsJsonAsync(Endpoint, dto);
        if (!responseMessage.IsSuccessStatusCode)
        {
            string content = await responseMessage.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
    }
}