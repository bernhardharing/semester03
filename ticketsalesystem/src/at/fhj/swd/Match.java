package at.fhj.swd;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NUC on 29.10.2016.
 */
@Entity
public class Match {
    @SequenceGenerator(name = "MatchIdGenerator",
            sequenceName = "Match_Sequence",
            allocationSize = 1)
    @Id
    @GeneratedValue(generator = "MatchIdGenerator")
    @Column(name = "id")
    private Integer id;
    private Date date;
    private Time time;

    public Match() {
    }

    public Match(Date date, Time time) {
        this.date = date;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(Team team) {
        this.teams.add(team);
    }

    //     TODO: implement ManyToMany
    @ManyToMany
    @JoinTable(name = "team_match",
            joinColumns =
            @JoinColumn(name = "fk_match_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "fk_team_id", referencedColumnName = "id")

    )
    private List<Team> teams = new ArrayList<>();


    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Column(name = "time")
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        if (id != null ? !id.equals(match.id) : match.id != null) return false;
        if (date != null ? !date.equals(match.date) : match.date != null) return false;
        if (time != null ? !time.equals(match.time) : match.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
