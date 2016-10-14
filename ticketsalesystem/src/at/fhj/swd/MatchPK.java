package at.fhj.swd;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by NUC on 14.10.2016.
 */
public class MatchPK implements Serializable {
    private Date pkDate;
    private Time pkTime;

    @Column(name = "pk_date")
    @Id
    public Date getPkDate() {
        return pkDate;
    }

    public void setPkDate(Date pkDate) {
        this.pkDate = pkDate;
    }

    @Column(name = "pk_time")
    @Id
    public Time getPkTime() {
        return pkTime;
    }

    public void setPkTime(Time pkTime) {
        this.pkTime = pkTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchPK matchPK = (MatchPK) o;

        if (pkDate != null ? !pkDate.equals(matchPK.pkDate) : matchPK.pkDate != null) return false;
        if (pkTime != null ? !pkTime.equals(matchPK.pkTime) : matchPK.pkTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pkDate != null ? pkDate.hashCode() : 0;
        result = 31 * result + (pkTime != null ? pkTime.hashCode() : 0);
        return result;
    }
}
