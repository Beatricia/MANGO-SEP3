package mango.sep3.databaseaccess.Shared;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Address", schema = "locally")
public class Address implements Serializable
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "city")
  private String city;

  @Column(name = "street")
  private String street;

  @Column(name = "zip")
  private String zip;

  @OneToMany(mappedBy = "address")
  private Set<Customer> customers = new HashSet<>();

  @OneToMany(mappedBy = "address")
  private Set<Farm> farms = new HashSet<>();


  public Address(String city, String street, String zip)
  {
    this.city = city;
    this.street = street;
    this.zip = zip;
  }

  public Address()
  {

  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getCity()
  {
    return city;
  }

  public void setCity(String city)
  {
    this.city = city;
  }

  public String getStreet()
  {
    return street;
  }

  public void setStreet(String street)
  {
    this.street = street;
  }

  public String getZip()
  {
    return zip;
  }

  public void setZip(String zip)
  {
    this.zip = zip;
  }
}
