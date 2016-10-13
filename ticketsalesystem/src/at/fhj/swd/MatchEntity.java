package at.fhj.swd;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by NUC on 13.10.2016.
 */
@Entity
@Table(name = "match", schema = "public", catalog = "haring")
@IdClass(MatchEntityPK.class)
public class MatchEntity {
    private Date pkDate;
    private Time pkTime;
    private String stadium;

    @Id
    @Column(name = "pk_date")
    public Date getPkDate() {
        return pkDate;
    }

    public void setPkDate(Date pkDate) {
        this.pkDate = pkDate;
    }

    @Id
    @Column(name = "pk_time")
    public Time getPkTime() {
        return pkTime;
    }

    public void setPkTime(Time pkTime) {
        this.pkTime = pkTime;
    }

    @Basic
    @Column(name = "stadium")
    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchEntity that = (MatchEntity) o;

        if (pkDate != null ? !pkDate.equals(that.pkDate) : that.pkDate != null) return false;
        if (pkTime != null ? !pkTime.equals(that.pkTime) : that.pkTime != null) return false;
        if (stadium != null ? !stadium.equals(that.stadium) : that.stadium != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pkDate != null ? pkDate.hashCode() : 0;
        result = 31 * result + (pkTime != null ? pkTime.hashCode() : 0);
        result = 31 * result + (stadium != null ? stadium.hashCode() : 0);
        return result;
    }
}
