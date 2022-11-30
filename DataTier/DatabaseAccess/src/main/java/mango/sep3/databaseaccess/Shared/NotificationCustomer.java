package mango.sep3.databaseaccess.Shared;

import javax.persistence.*;

@Entity
@Table(name = "NotificationCustomer", schema = "locally")
public class NotificationCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String message;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    private Customer customer;

    @Column
    private long createdAt;

    public NotificationCustomer() {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
