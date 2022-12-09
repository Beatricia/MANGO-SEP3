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
  private Offer offerId;

  @Column(name = "reason")
  private String reason;

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
    return offerId;
  }

  public void setOffer(Offer offer)
  {
    this.offerId = offer;
  }

  public String getReason()
  {
    return reason;
  }

  public void setReason(String reason)
  {
    this.reason = reason;
  }
}
