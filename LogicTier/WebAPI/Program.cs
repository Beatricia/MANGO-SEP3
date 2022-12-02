using System.Text;
using Application.DAOInterfaces;
using Application.LogicImplementations;
using Application.LogicInterfaces;
using GprcClients.DAOImplementations;
using WebAPI.Utils;
using Grpc.Net.ClientFactory;
using WebAPI.Utils;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.IdentityModel.Tokens;
using Microsoft.OpenApi.Models;
using Shared.Auth;
using Swashbuckle.AspNetCore.Filters;
using Address = Shared.Models.Address;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(options =>
{
    options.OperationFilter<SecurityRequirementsOperationFilter>(true, "Bearer");
    options.AddSecurityDefinition("Bearer", new OpenApiSecurityScheme
    {
        Name = "Authorization",
        Type = SecuritySchemeType.ApiKey,
        Scheme = "Bearer",
        BearerFormat = "JWT",
        In = ParameterLocation.Header,
        Description = "Standard Authorization header using the Bearer scheme (JWT). Example: \"bearer {token}\"",
    });
    options.AddSecurityRequirement(new OpenApiSecurityRequirement {
        {
            new OpenApiSecurityScheme {
                Reference = new OpenApiReference {
                    Type = ReferenceType.SecurityScheme,
                    Id = "Bearer"
                }
            },
            Array.Empty<string>()
        }
    });
    options.OperationFilter<AppendAuthorizeToSummaryOperationFilter>();
});



Action<GrpcClientFactoryOptions> grpcOptions = options =>
{
    options.Address = new Uri("http://localhost:6565");
};

// add here the dependency injections
builder.Services.AddGrpcClient<FarmService.FarmServiceClient>(grpcOptions);
builder.Services.AddScoped<IFarmDao, FarmDaoImpl>();
builder.Services.AddScoped<IFarmLogic, FarmLogic>();

builder.Services.AddGrpcClient<OfferService.OfferServiceClient>(grpcOptions);
builder.Services.AddScoped<IOfferDao, OfferDaoImpl>();
builder.Services.AddScoped<IOfferLogic,OfferLogic>();
builder.Services.AddScoped<IOrderLogic, OrderLogic>();
builder.Services.AddScoped<IOrderDao, OrderDaoImpl>();

builder.Services.AddGrpcClient<UserService.UserServiceClient>(grpcOptions);
builder.Services.AddScoped<IAuthDao, AuthDaoImpl>();
builder.Services.AddScoped<IAuthLogic, AuthLogic>();
builder.Services.AddScoped<IUserLogic, UserLogic>();
builder.Services.AddScoped<IUserDao, UserDaoImpl>();

builder.Services.AddGrpcClient<OrderService.OrderServiceClient>(grpcOptions);

builder.Services.AddGrpcClient<CartOfferService.CartOfferServiceClient>(grpcOptions);
builder.Services.AddScoped<ICartDao, CartDaoImpl>();
builder.Services.AddScoped<ICartLogic, CartLogic>();

builder.Services.AddGrpcClient<NotificationService.NotificationServiceClient>(grpcOptions);
builder.Services.AddScoped<INotificationDao, NotificationDaoImpl>();
builder.Services.AddScoped<INotificationLogic, NotificationLogic>();

builder.Services.AddTransient<IImageDao, ImageResource>();
builder.Services.AddTransient<ImageResource>();
builder.Services.AddTransient<IFarmIconDao, FarmIconResource>();

builder.Services.AddHttpContextAccessor();

builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme).AddJwtBearer(options =>
{
    options.RequireHttpsMetadata = false;
    options.SaveToken = true;
    options.TokenValidationParameters = new TokenValidationParameters()
    {
        ValidateIssuer = true,
        ValidateAudience = true,
        ValidAudience = builder.Configuration["Jwt:Audience"],
        ValidIssuer = builder.Configuration["Jwt:Issuer"],
        IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(builder.Configuration["Jwt:Key"]))
    };
});

AuthorizationPolicies.AddPolicies(builder.Services);

builder.Services.AddHttpClient();
builder.Services.AddSingleton<IAddressDao, AddressLocationIQ>();




var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI(options =>
    {
        options.EnableTryItOutByDefault();
        options.OAuthScopes("bearer");
    });
}

app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseAuthentication();

app.UseAuthorization();

app.MapControllers();

app.UseCors(x => x
    .AllowAnyMethod()
    .AllowAnyHeader()
    .SetIsOriginAllowed(origin => true) // allow any origin
    .AllowCredentials());

var addressDao = app.Services.GetRequiredService<IAddressDao>();
var coordinates = await addressDao.GetCoordinatesAsync(new Shared.Models.Address()
{
    City = "Aarhus",
    Street = "Skovdalsvej",
    ZIP = "8260"
});

Console.WriteLine(coordinates.Latitude + " " + coordinates.Longitude);

app.Run();