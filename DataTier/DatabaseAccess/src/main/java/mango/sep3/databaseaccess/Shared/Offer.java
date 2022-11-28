package mango.sep3.databaseaccess.Shared;

import mango.sep3.databaseaccess.protobuf.OfferItems;

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


}
