package mango.sep3.databaseaccess.Shared;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="review", schema = "locally")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @JoinColumn
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Customer customer;

    @JoinColumn
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Farm farm;

    @Column
    private long createdAt;

    @Column
    private String reviewText;

    public Review() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }
}