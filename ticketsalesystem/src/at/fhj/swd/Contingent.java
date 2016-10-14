package at.fhj.swd;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by NUC on 14.10.2016.
 */
@Entity
public class Contingent {
    private Integer id;
    private Integer quantity;
    private Date fkMatchDate;
    private Time fkMatchTime;

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

    @Basic
    @Column(name = "fk_match_date")
    public Date getFkMatchDate() {
        return fkMatchDate;
    }

    public void setFkMatchDate(Date fkMatchDate) {
        this.fkMatchDate = fkMatchDate;
    }

    @Basic
    @Column(name = "fk_match_time")
    public Time getFkMatchTime() {
        return fkMatchTime;
    }

    public void setFkMatchTime(Time fkMatchTime) {
        this.fkMatchTime = fkMatchTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contingent that = (Contingent) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (fkMatchDate != null ? !fkMatchDate.equals(that.fkMatchDate) : that.fkMatchDate != null) return false;
        if (fkMatchTime != null ? !fkMatchTime.equals(that.fkMatchTime) : that.fkMatchTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (fkMatchDate != null ? fkMatchDate.hashCode() : 0);
        result = 31 * result + (fkMatchTime != null ? fkMatchTime.hashCode() : 0);
        return result;
    }
}
