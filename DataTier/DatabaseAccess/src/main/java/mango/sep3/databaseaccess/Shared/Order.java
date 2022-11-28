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

  @OneToMany(mappedBy = "order", cascade = CascadeType.REFRESH)
  private Set<OrderOffer> orderOffers;

  @Column
  private boolean isDone;

  @Column
  private String farmName;

  @Column
  private String collectionOption;

  public int getId()
  {
    return id;
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
    return isDone;
  }

  public void setDone(boolean done)
  {
    isDone = done;
  }

  public String getFarmName()
  {
    return farmName;
  }

  public void setFarmName(String farmName)
  {
    this.farmName = farmName;
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
