package at.fhj.swd;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by NUC on 14.10.2016.
 */
@Entity
@Table(name = "team_match", schema = "public", catalog = "haring")
public class TeamMatch {
    private Integer pkId;
    private Integer fkTeamAId;
    private Integer fkTeamBId;
    private Date fkMatchDate;
    private Time fkMatchTime;

    @Id
    @Column(name = "pk_id")
    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    @Basic
    @Column(name = "fk_team_a_id")
    public Integer getFkTeamAId() {
        return fkTeamAId;
    }

    public void setFkTeamAId(Integer fkTeamAId) {
        this.fkTeamAId = fkTeamAId;
    }

    @Basic
    @Column(name = "fk_team_b_id")
    public Integer getFkTeamBId() {
        return fkTeamBId;
    }

    public void setFkTeamBId(Integer fkTeamBId) {
        this.fkTeamBId = fkTeamBId;
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

        TeamMatch teamMatch = (TeamMatch) o;

        if (pkId != null ? !pkId.equals(teamMatch.pkId) : teamMatch.pkId != null) return false;
        if (fkTeamAId != null ? !fkTeamAId.equals(teamMatch.fkTeamAId) : teamMatch.fkTeamAId != null) return false;
        if (fkTeamBId != null ? !fkTeamBId.equals(teamMatch.fkTeamBId) : teamMatch.fkTeamBId != null) return false;
        if (fkMatchDate != null ? !fkMatchDate.equals(teamMatch.fkMatchDate) : teamMatch.fkMatchDate != null)
            return false;
        if (fkMatchTime != null ? !fkMatchTime.equals(teamMatch.fkMatchTime) : teamMatch.fkMatchTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pkId != null ? pkId.hashCode() : 0;
        result = 31 * result + (fkTeamAId != null ? fkTeamAId.hashCode() : 0);
        result = 31 * result + (fkTeamBId != null ? fkTeamBId.hashCode() : 0);
        result = 31 * result + (fkMatchDate != null ? fkMatchDate.hashCode() : 0);
        result = 31 * result + (fkMatchTime != null ? fkMatchTime.hashCode() : 0);
        return result;
    }
}
