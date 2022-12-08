package mango.sep3.databaseaccess.Shared;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Admin", schema = "locally")
public class Admin
{
  @Id
  @Column(name = "username")
  private String username;

  public Admin(){

  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }
}
