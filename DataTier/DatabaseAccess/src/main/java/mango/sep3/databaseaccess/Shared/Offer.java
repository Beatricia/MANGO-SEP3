package mango.sep3.databaseaccess.Shared;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="OFFERS", schema = "locally")
public class Offer implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offerId")
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column (nullable = false, length = 100)
    private double price;

    @Column (nullable = false, length = 20)
    private String unit;

    @Column(nullable = true)
    private String description;

    @Column
    private String imgPath;

    @Column
    private int quantity;

    @Column(nullable = true)
    private boolean pickUp;

    @Column(nullable = true)
    private boolean delivery;

    @Column(nullable = true)
    private boolean pickyourOwn;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "farmId", nullable = false)
    private Farm farm;

    @OneToMany(mappedBy="offer", cascade = CascadeType.ALL)
    private Set<OrderOffer> orderOffers;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getImgPath()
    {
        return imgPath;
    }

    public void setImgPath(String imgPath)
    {
        this.imgPath = imgPath;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public boolean isPickUp()
    {
        return pickUp;
    }

    public void setPickUp(boolean pickUp)
    {
        this.pickUp = pickUp;
    }

    public boolean isDelivery()
    {
        return delivery;
    }

    public void setDelivery(boolean delivery)
    {
        this.delivery = delivery;
    }

    public boolean isPickyourOwn()
    {
        return pickyourOwn;
    }

    public void setPickyourOwn(boolean pickyourOwn)
    {
        this.pickyourOwn = pickyourOwn;
    }

    public Farm getFarm()
    {
        return farm;
    }

    public void setFarm(Farm farm)
    {
        this.farm = farm;
    }

    public Set<OrderOffer> getOrderOffers()
    {
        return orderOffers;
    }

    public void setOrderOffers(Set<OrderOffer> orderOffers)
    {
        this.orderOffers = orderOffers;
    }
}
