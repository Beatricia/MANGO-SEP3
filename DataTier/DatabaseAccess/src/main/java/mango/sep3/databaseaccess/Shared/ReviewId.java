package mango.sep3.databaseaccess.Shared;

import java.io.Serializable;
import java.util.Objects;

public class ReviewId implements Serializable {
    private String customerName;
    private String farmName;

    public ReviewId() {
    }


    public ReviewId(String customerName, String farmName) {
        this.customerName = customerName;
        this.farmName = farmName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewId reviewId = (ReviewId) o;
        return Objects.equals(customerName, reviewId.customerName) && Objects.equals(farmName, reviewId.farmName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName, farmName);
    }
}
