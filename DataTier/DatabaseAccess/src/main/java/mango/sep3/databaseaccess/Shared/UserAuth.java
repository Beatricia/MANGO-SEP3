package mango.sep3.databaseaccess.Shared;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="UserAuth", schema = "locally")
public class UserAuth implements Serializable {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name="hash")
    private String hash;

    @Column(name = "salt")
    private String salt;

    public UserAuth(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
