package mango.sep3.databaseaccess.Shared;

import javax.persistence.*;

@Entity
@Table(name = "Report", schema = "locally")
public class Report
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "reportId")
  private long id;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "offerId")
  private Offer offer;

  @Column(name = "reason")
  private String reason;

  @JoinColumn
  @ManyToOne(cascade = CascadeType.MERGE)
  private Customer customer;

  public Report(){};

  public long getId()
  {
    return id;
  }

  public void setId(long id)
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

  public String getReason()
  {
    return reason;
  }

  public void setReason(String reason)
  {
    this.reason = reason;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }
}
