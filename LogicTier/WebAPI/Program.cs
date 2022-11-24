using Application.DAOInterfaces;
using Application.LogicImplementations;
using Application.LogicInterfaces;
using GprcClients.DAOImplementations;
using Grpc.Net.ClientFactory;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
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


var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI(options => options.EnableTryItOutByDefault());
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();
app.UseCors(x => x
    .AllowAnyMethod()
    .AllowAnyHeader()
    .SetIsOriginAllowed(origin => true) // allow any origin
    .AllowCredentials());

app.Run();