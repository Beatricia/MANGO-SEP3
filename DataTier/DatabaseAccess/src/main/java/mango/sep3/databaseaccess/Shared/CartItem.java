package mango.sep3.databaseaccess.Shared;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="CartItem", schema = "locally")
public class CartItem implements Serializable
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name= "cartItemId")
  private int cartItemId;


  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "offerId")
  private Offer offerId;

  @Column(name = "quantity")
  private int quantity;

  @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
  @JoinColumn(name = "customer", referencedColumnName = "username")
  private Customer customer;

  @Column(name  = "collectionOption", nullable = true)
  private int collectionOption;

  public CartItem(){

  }

  public int getCartItemId()
  {
    return cartItemId;
  }

  public void setCartItemId(int cartItemId)
  {
    this.cartItemId = cartItemId;
  }

  public Offer getOfferId()
  {
    return offerId;
  }

  public void setOfferId(Offer offerId)
  {
    this.offerId = offerId;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public void setQuantity(int quantity)
  {
    this.quantity = quantity;
  }

  public Customer getCustomer()
  {
    return customer;
  }

  public void setCustomer(Customer username)
  {
    this.customer = username;
  }

  public int getCollectionOption()
  {
    return collectionOption;
  }

  public void setCollectionOption(int collectionOption)
  {
    this.collectionOption = collectionOption;
  }
}

