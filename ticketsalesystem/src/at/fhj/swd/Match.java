package at.fhj.swd;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by bernhard on 18.10.2016.
 */
@Entity
public class Match {
    private Date date;
    private Time time;
    private String stadium;

    @Id
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Id
    @Column(name = "time")
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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

        Match match = (Match) o;

        if (date != null ? !date.equals(match.date) : match.date != null) return false;
        if (time != null ? !time.equals(match.time) : match.time != null) return false;
        if (stadium != null ? !stadium.equals(match.stadium) : match.stadium != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (stadium != null ? stadium.hashCode() : 0);
        return result;
    }
}
