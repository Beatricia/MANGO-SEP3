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


  @OneToOne(targetEntity = Offer.class)
  @JoinColumn(name = "offerId")
  private Offer offerId;

  @Column(name = "quantity")
  private int quantity;

  @OneToOne
  @JoinColumn(name = "userName")
  private User username;

  @Column(name  = "collectionOption")
  private String collectionOption;

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

  public User getUsername()
  {
    return username;
  }

  public void setUsername(User username)
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
}

