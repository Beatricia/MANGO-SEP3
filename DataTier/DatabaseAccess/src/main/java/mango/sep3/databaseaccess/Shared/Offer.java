package mango.sep3.databaseaccess.Shared;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="OFFERS", schema = "locally")
public class Offer implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column (nullable = false, length = 100)
    private double price;

    @Column (nullable = false, length = 20)
    private String unit;

    // add column annotation to fields

    @Column
    private boolean delivery;

    @Column
    private boolean pickUp;

    @Column
    private boolean pickYourOwn;

    @Column
    private String description;

    @Column
    private String image;


    public Offer(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public boolean isPickUp() {
        return pickUp;
    }

    public void setPickUp(boolean pickUp) {
        this.pickUp = pickUp;
    }

    public boolean isPickYourOwn() {
        return pickYourOwn;
    }

    public void setPickYourOwn(boolean pickYourOwn) {
        this.pickYourOwn = pickYourOwn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
