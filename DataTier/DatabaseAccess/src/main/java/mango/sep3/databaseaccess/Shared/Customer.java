package mango.sep3.databaseaccess.Shared;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Customer", schema = "locally")
public class Customer implements Serializable
{
  @Id
  @Column(name = "username")
  private String username;

  @Column(nullable = true, length = 100, name = "first_Name")
  private String firstName;

  @Column(nullable = true, length = 100, name = "last_Name")
  private String lastName;

  @Column(nullable = true, length = 100, name = "phone")
  private String phone;

  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinColumn(name = "address_id")
  private Address address;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn
  private Set<CartItem> cartItems;

  public Customer(String username, String firstName, String lastName,
      String phone)
  {
    super();
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
  }

  public Customer()
  {

  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public String getPhone()
  {
    return phone;
  }

  public void setPhone(String phone)
  {
    this.phone = phone;
  }

  public Address getAddress()
  {
    return address;
  }

  public void setAddress(Address address)
  {
    this.address = address;
  }
}
