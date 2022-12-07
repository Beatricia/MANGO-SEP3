namespace Shared.DTOs;

public class SearchOfferParameterDto
{
    public int? Distance { get; set; }
    public string? NameContains { get; set; }
    public bool? Delivery { get; set; }
    public bool? PickUp { get; set; }
    public bool? PickYO { get; set; }
    
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
    
    public SearchOfferParameterDto(){}
}