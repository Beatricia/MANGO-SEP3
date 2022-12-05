namespace Shared.DTOs;

public class SearchOfferParameterDto
{
    public int? Distance { get;}
    public string? NameContains { get;}
    public bool? Delivery { get;}
    public bool? PickUp { get;}
    public bool? PickYO { get;}
    
    public string? Username { get;} //we need it to get the user's address

    public SearchOfferParameterDto(string? username, int? distance, string? nameContains, bool? delivery, bool? pickUp, bool? pickYo)
    {
        Username = username;
        Distance = distance;
        NameContains = nameContains;
        Delivery = delivery;
        PickUp = pickUp;
        PickYO = pickYo;
    }
}