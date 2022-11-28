using Microsoft.AspNetCore.Components.Web;
using Microsoft.AspNetCore.Components.WebAssembly.Hosting;
using BlazorLocally;
using BlazorWasm.Auth;
using HttpClient.ClientImplementations;
using HttpClient.ClientInterfaces;
using Microsoft.AspNetCore.Components.Authorization;
using Shared.Auth;

var builder = WebAssemblyHostBuilder.CreateDefault(args);
builder.RootComponents.Add<App>("#app");
builder.RootComponents.Add<HeadOutlet>("head::after");

builder.Services.AddSingleton(new ApiAccess{ BaseApiAddress = "https://localhost:7086" });
builder.Services.AddScoped(sp => new System.Net.Http.HttpClient { BaseAddress = new Uri(sp.GetRequiredService<ApiAccess>().BaseApiAddress) });
builder.Services.AddScoped<IOfferService, OfferHttpClient>();
builder.Services.AddScoped<IFarmService, FarmHttpClient>();
builder.Services.AddScoped<IOrderService,OrderHttpClient>();
builder.Services.AddScoped<IAuthService, AuthHttpClient>();
builder.Services.AddScoped<IUserService, UserHttpClient>();
builder.Services.AddScoped<ICartService, CartHttpClient>();
builder.Services.AddScoped<AuthenticationStateProvider, CustomAuthProvider>();

AuthorizationPolicies.AddPolicies(builder.Services); //idk if this should be here

var app = builder.Build();

await app.RunAsync();


// TODO: https://www.youtube.com/watch?v=iedIu9H982Q