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
    private String imgPath; // TODO: Remove image path from here

    @Column
    private int quantity;

    @Column(nullable = true)
    private int collectionOption;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "farmId", nullable = false, referencedColumnName = "id")
    private Farm farm;

    @OneToMany(mappedBy="offer", cascade = CascadeType.ALL)
    private Set<OrderOffer> orderOffers;

    @Column(name = "is_disabled")
    private boolean isDisabled = false;

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

    public int getCollectionOption()
    {
        return collectionOption;
    }

    public void setCollectionOption(int collectionOption)
    {
        this.collectionOption = collectionOption;
    }
    public boolean getIsDisabled()
    {
        return isDisabled;
    }

    public void setIsDisabled(boolean isDisabled)
    {
        this.isDisabled = isDisabled;
    }
}
