package at.fhj.swd;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by NUC on 14.10.2016.
 */
@Entity
public class Match {
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
}
