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
    @ManyToOne private Integer fkTeamAId;
    @ManyToOne private Integer fkTeamBId;
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

    @Column(name = "fk_team_a_id")
    public Integer getFkTeamAId() {
        return fkTeamAId;
    }

    public void setFkTeamAId(Integer fkTeamAId) {
        this.fkTeamAId = fkTeamAId;
    }

    @Column(name = "fk_team_b_id")
    public Integer getFkTeamBId() {
        return fkTeamBId;
    }

    public void setFkTeamBId(Integer fkTeamBId) {
        this.fkTeamBId = fkTeamBId;
    }

    @Column(name = "fk_match_date")
    public Date getFkMatchDate() {
        return fkMatchDate;
    }

    public void setFkMatchDate(Date fkMatchDate) {
        this.fkMatchDate = fkMatchDate;
    }

    @Column(name = "fk_match_time")
    public Time getFkMatchTime() {
        return fkMatchTime;
    }

    public void setFkMatchTime(Time fkMatchTime) {
        this.fkMatchTime = fkMatchTime;
    }
}
