package mango.sep3.databaseaccess.Shared;

import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="FARMS", schema = "locally")
public class Farm implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 100, name = "name")
    private String name;

    @Column(name = "phoneNumber")
    private String phone;

    @Column(nullable = true, name = "description")
    private String description;

    @Column(name = "deliveryDistance")
    private int deliveryDistance;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private Set<Offer> offers;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name="farmer", nullable=true, referencedColumnName = "username")
    private Farmer farmer;

    @Column(name = "iconpath")
    private String iconPath;

    @Column(name = "isDisabled")
    private boolean isDisabled = false;

    public Farm()
    {}

    public Farm(String name, String phone, String description,
        int deliveryDistance, Address address, Farmer farmer)
    {
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.deliveryDistance = deliveryDistance;
        this.address = address;
        //this.farmer = farmer;
    }
    public Farm(String name, String phone, String description,
        int deliveryDistance, Address address, Set<Offer> offers)
    {
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.deliveryDistance = deliveryDistance;
        this.address = address;
        this.offers = offers;
    }

    //TODO add Farmer reference (nut sure if it should be a new Farmer table or just from the User)


    public Farm(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getDeliveryDistance()
    {
        return deliveryDistance;
    }

    public void setDeliveryDistance(int deliveryDistance)
    {
        this.deliveryDistance = deliveryDistance;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }
    public Set<Offer> getOffers()
    {
        return offers;
    }

    public void setOffers(Set<Offer> offers)
    {
        this.offers = offers;
    }

    public Farmer getFarmer()
    {
        return farmer;
    }

    public void setFarmer(Farmer farmer)
    {
        this.farmer = farmer;
    }


    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }
}
