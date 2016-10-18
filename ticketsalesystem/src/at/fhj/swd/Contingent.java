package at.fhj.swd;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by bernhard on 18.10.2016.
 */
@Entity
public class Contingent {
    private Integer id;
    private Integer quantity;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contingent that = (Contingent) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }
}
