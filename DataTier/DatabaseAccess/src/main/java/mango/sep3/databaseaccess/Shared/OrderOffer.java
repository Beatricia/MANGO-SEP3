package mango.sep3.databaseaccess.Shared;

import javax.persistence.*;
import java.util.Set;

@Entity @Table(name = "OrderOffers", schema = "locally") public class OrderOffer
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @ManyToOne
  @JoinColumn(name="offerId")
  private Offer offer;

  @ManyToOne
  @JoinColumn(name="orderId")
  private Order order;


  @Column(name = "quantity")
  private int quantity;

  @Column(name = "username")
  private String username;

  @Column(name = "collectionOption")
  private String collectionOption;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public Offer getOffer()
  {
    return offer;
  }

  public void setOffer(Offer offer)
  {
    this.offer = offer;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public void setQuantity(int quantity)
  {
    this.quantity = quantity;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getCollectionOption()
  {
    return collectionOption;
  }

  public void setCollectionOption(String collectionOption)
  {
    this.collectionOption = collectionOption;
  }

  public Order getOrder()
  {
    return order;
  }

  public void setOrder(Order order)
  {
    this.order = order;
  }
}
