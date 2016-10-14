package at.fhj.swd;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by NUC on 13.10.2016.
 */
@Entity
@Table(name = "contingent", schema = "public", catalog = "haring")
public class ContingentEntity {
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

}
