﻿using Application.DAOInterfaces;
using Google.Protobuf;

namespace GprcClients.Converters;

/// <summary>
/// Extension methods to convert objects to and from protobuf messages.
/// </summary>
internal static class ConverterExtensions
{
    
    #region Id

    public static Id ToGrpc(this int id)
    {
        return new Id { Id_ = id };
    }

    #endregion
    
    #region Text
    
    public static string ToString(this Text message)
    {
        return message.Text_;
    }
    
    public static Text ToGrpc(this string text)
    {
        return new Text { Text_ = text };
    }

    #endregion

    #region Auth User

    public static UserAuth ToGrpc(this Shared.Models.UserAuth message)
    {
        return new UserAuth
        {
            Username = message.Username,
            Hash = message.HashPassword,
            Salt = message.Salt,
        };
    }
    
    public static Shared.Models.UserAuth ToShared(this UserAuth message)
    {
        return new Shared.Models.UserAuth
        {
            Username = message.Username,
            HashPassword = message.Hash,
            Salt = message.Salt,
        };
    }
    
    #endregion

    #region Address 

    public static Shared.Models.Address ToShared(this Address message)
    {
        return new Shared.Models.Address
        {
            Street = message.Street,
            City = message.City,
            ZIP = message.Zip,
            Latitude = message.Latitude,
            Longitude = message.Longitude,
        };
    }
    
    public static Address ToGrpc(this Shared.Models.Address message)
    {
        return new Address
        {
            Street = message.Street,
            City = message.City,
            Zip = message.ZIP,
            Latitude = message.Latitude,
            Longitude = message.Longitude,
        };
    }

    #endregion

    #region CartOffer

    public static CartOffer ToGrpc(this Shared.Models.CartOffer message)
    {
        return new CartOffer
        {
            Id = message.Id,
            Offer = message.Offer.ToGrpc(),
            Quantity = message.Quantity,
            Username = message.UserName,
            CollectionOption = message.CollectionOption,
        };
    }
    
    public static Shared.Models.CartOffer ToShared(this CartOffer message, IImageDao imageDao)
    {
        return new Shared.Models.CartOffer
        {
            Id = message.Id,
            Offer = message.Offer.ToShared(imageDao),
            Quantity = message.Quantity,
            UserName = message.Username,
            CollectionOption = message.CollectionOption
        };
    } 

    #endregion
    
    #region Customer
    
    public static Shared.Models.Customer ToShared(this Customer customer)
    {
        var sharedCustomer = new Shared.Models.Customer
        {
            Username = customer.Username,
            FirstName = customer.Firstname,
            LastName = customer.Lastname,
            Phone = customer.Phone,
            Address = customer.Address.ToShared(),
        };
        
        return sharedCustomer;
    }
    
    public static Customer ToGrpc(this Shared.Models.Customer customer)
    {
        var grpcCustomer = new Customer
        {
            Username = customer.Username,
            Firstname = customer.FirstName,
            Lastname = customer.LastName,
            Phone = customer.Phone,
            Address = customer.Address.ToGrpc(),
        };
        
        return grpcCustomer;
    }
    
    #endregion

    #region Farmer

    public static Shared.Models.Farmer ToShared(this Farmer farmer)
    {
        var sharedFarmer = new Shared.Models.Farmer
        {
            Username = farmer.Username,
            FirstName = farmer.Firstname,
            LastName = farmer.Lastname,
        };
        
        return sharedFarmer;
    }
    
    public static Farmer ToGrpc(this Shared.Models.Farmer farmer)
    {
        var grpcFarmer = new Farmer
        {
            Username = farmer.Username,
            Firstname = farmer.FirstName,
            Lastname = farmer.LastName,
        };
        
        return grpcFarmer;
    }
    
    #endregion

    #region Notification

    public static Shared.Models.Notification ToShared(this Notification notification)
    {
        return new Shared.Models.Notification
        {
            Id = notification.Id,
            Text = notification.Text,
            CreatedAt = new DateTime(notification.CreatedAt),
            ToUsername = notification.ToUsername
        };
    }
    
    public static Notification ToGrpc(this Shared.Models.Notification notification)
    {
        return new Notification
        {
            Id = notification.Id,
            Text = notification.Text,
            CreatedAt = notification.CreatedAt.Ticks,
            ToUsername = notification.ToUsername
        };
    }

    #endregion

    #region Offer

    public static Shared.Models.Offer ToShared(this Offer offer, IImageDao imageDao)
    {
        return new Shared.Models.Offer
        {
            Id = offer.Id,
            Name = offer.Name,
            Quantity = offer.Quantity,
            Unit = offer.Unit,
            Price = offer.Price,
            Delivery = offer.Delivery,
            PickUp = offer.PickUp,
            PickYourOwn = offer.PickYourOwn,
            Description = offer.Description,
            Image = imageDao.GetImageForOffer(offer.Id),
            FarmName = offer.FarmName
        };
    }
    
    public static Offer ToGrpc(this Shared.Models.Offer offer)
    {
        return new Offer
        {
            Id = offer.Id,
            Name = offer.Name,
            Quantity = offer.Quantity,
            Unit = offer.Unit,
            Price = offer.Price,
            Delivery = offer.Delivery,
            PickUp = offer.PickUp,
            PickYourOwn = offer.PickYourOwn,
            Description = offer.Description,
            FarmName = offer.FarmName,
        };
    }

    #endregion

    #region OrderOffer

    public static Shared.Models.OrderOffer ToShared(this OrderOffer orderOffer, IImageDao imageDao)
    {
        return new Shared.Models.OrderOffer
        {
            Id = orderOffer.Id,
            CollectionOption = orderOffer.CollectionOption,
            Offer = orderOffer.Offer.ToShared(imageDao),
            Quantity = orderOffer.Quantity,
            Username = orderOffer.Username,
            FarmName = orderOffer.Offer.FarmName,
        };
    }
    
    public static OrderOffer ToGrpc(this Shared.Models.OrderOffer orderOffer)
    {
        return new OrderOffer
        {
            Id = orderOffer.Id,
            CollectionOption = orderOffer.CollectionOption,
            Offer = orderOffer.Offer.ToGrpc(),
            Quantity = orderOffer.Quantity,
            Username = orderOffer.Username,
        };
    }

    #endregion
    
    #region Order
    
    public static Shared.Models.Order ToShared(this Order order, IImageDao imageDao)
    {
        return new Shared.Models.Order
        {
            Id = order.Id,
            CollectionOption = order.CollectionOption,
            FarmName = order.FarmName,
            IsDone = order.IsDone,
            OrderOffers = order.OrderOffers.Select(orderOffer => orderOffer.ToShared(imageDao)).ToList(),
            Username = order.Username
        };
    }
    
    public static Order ToGrpc(this Shared.Models.Order order)
    {
        return new Order
        {
            Id = order.Id,
            CollectionOption = order.CollectionOption,
            FarmName = order.FarmName,
            IsDone = order.IsDone,
            OrderOffers = { order.OrderOffers.Select(orderOffer => orderOffer.ToGrpc()) },
            Username = order.Username
        };
    }
    
    #endregion
    
    #region Farm
    
    public static Shared.Models.Farm ToShared(this Farm farm, IFarmIconDao farmIconDao)
    {
        return new Shared.Models.Farm
        {
            Name = farm.Name,
            Phone = farm.Phone,
            Address = farm.Address.ToShared(),
            DeliveryDistance = farm.DeliveryDistance,
            FarmStatus = farm.FarmStatus,
            Farmer = farm.Farmer.ToShared(),
            FarmIcon = farmIconDao.CreateIcon(farm.IconPath),
        };
    }
    
    public static Farm ToGrpc(this Shared.Models.Farm farm)
    {
        return new Farm
        {
            Name = farm.Name,
            Phone = farm.Phone,
            Address = farm.Address.ToGrpc(),
            DeliveryDistance = farm.DeliveryDistance,
            FarmStatus = farm.FarmStatus,
            Farmer = farm.Farmer.ToGrpc(),
            IconPath = farm.FarmIcon.FileName
        };
    }
    
    #endregion

    #region RepeatedUsername

    public static ICollection<string> ToShared(this RepeatedUsername repeatedUsernames)
    {
        return repeatedUsernames.Username.Select(repeatedUsername => repeatedUsername.Text_).ToList();
    }
    

    #endregion
}