package at.fhj.swd;

import javax.persistence.*;

/**
 * Created by NUC on 29.10.2016.
 */
@Entity
public class Contingent {
    @SequenceGenerator( name="ContingentIdGenerator",
            sequenceName="Contingent_Sequence",
            allocationSize = 1)
    @Id @GeneratedValue(generator="ContingentIdGenerator")
    @Column(name = "id")
    private Integer id;
    private Integer quantity;


    @ManyToOne
    @JoinColumn(name="fk_match_id")
    private Match match;

    public Contingent(Integer quantity, Match match) {
        this.quantity = quantity;
        this.match = match;
    }

    public Contingent() {}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
