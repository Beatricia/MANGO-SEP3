package mango.sep3.databaseaccess.Shared;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Farmer", schema = "locally")
public class Farmer
{
  @Id
  @Column(name = "username")
  private String username;

  @Column(name = "firstname")
  private String firstName;

  @Column(name = "lastname")
  private String lastName;

  @OneToMany(mappedBy="farmer")
  private Set<Farm> farms;


  public Farmer(){}

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
