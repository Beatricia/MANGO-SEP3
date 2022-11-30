package mango.sep3.databaseaccess.Shared;


import javax.persistence.*;

@Entity
@Table(name = "Notification", schema = "locally")
public class NotificationFarmer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String message;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    private Farmer farmer;

    @Column
    private long createdAt;

    public NotificationFarmer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }
}
