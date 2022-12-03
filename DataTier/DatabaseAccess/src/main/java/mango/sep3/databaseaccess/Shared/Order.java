package mango.sep3.databaseaccess.Shared;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Order", schema = "locally")
public class Order
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "orderId")
  private int id;

  @OneToMany(mappedBy = "order", cascade = {CascadeType.REFRESH, CascadeType.REMOVE},fetch = FetchType.EAGER)
  private Set<OrderOffer> orderOffers;

  @Column
  private boolean done;

  @Column
  private String farmName;

  @Column
  private int collectionOption;

  @Transient
  private String username;

  @Transient
  private Set<CartItem> cartItems;

  public String getUsername()
  {
    return username;
  }


  public void setId(int id)
  {
    this.id = id;
  }

  public Set<OrderOffer> getOrderOffers()
  {
    return orderOffers;
  }

  public void setOrderOffers(Set<OrderOffer> orderOffers)
  {
    this.orderOffers = orderOffers;
  }

  public boolean isDone()
  {
    return done;
  }

  public void setDone(boolean done)
  {
    this.done = done;
  }

  public String getFarmName()
  {
    return farmName;
  }

  public void setFarmName(String farmName)
  {
    this.farmName = farmName;
  }

  public int getCollectionOption()
  {
    return collectionOption;
  }

  public void setCollectionOption(int collectionOption)
  {
    this.collectionOption = collectionOption;
  }
  public void setUsername(String username)
  {
    this.username = username;
  }
  public int getId()
  {
    return id;
  }
  public Set<CartItem> getCartItems()
  {
    return cartItems;
  }

  public void setCartItems(Set<CartItem> cartItems)
  {
    this.cartItems = cartItems;
  }
}
