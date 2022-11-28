using Microsoft.AspNetCore.Components.Web;
using Microsoft.AspNetCore.Components.WebAssembly.Hosting;
using BlazorLocally;
using BlazorLocally.Utils;
using HttpClient.ClientImplementations;
using HttpClient.ClientInterfaces;

var builder = WebAssemblyHostBuilder.CreateDefault(args);
builder.RootComponents.Add<App>("#app");
builder.RootComponents.Add<HeadOutlet>("head::after");

builder.Services.AddSingleton(new ApiInformation{ BaseAddress = "https://localhost:7086" });
builder.Services.AddScoped(sp => new System.Net.Http.HttpClient { BaseAddress = new Uri(sp.GetRequiredService<ApiInformation>().BaseAddress) });
builder.Services.AddScoped<IOfferService, OfferHttpClient>();
builder.Services.AddScoped<IFarmService, FarmHttpClient>();
builder.Services.AddScoped<IOrderService,OrderHttpClient>();
builder.Services.AddScoped<IAuthService, AuthHttpClient>();
builder.Services.AddScoped<IUserService, UserHttpClient>();
builder.Services.AddScoped<ICartService, CartHttpClient>();

var app = builder.Build();

await app.RunAsync();


// TODO: https://www.youtube.com/watch?v=iedIu9H982Q