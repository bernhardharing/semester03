package at.fhj.swd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bernhard on 18.10.2016.
 */
@Entity
@Table(name = "team_match", schema = "public", catalog = "haring")
public class TeamMatch {
    private Integer id;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamMatch teamMatch = (TeamMatch) o;

        if (id != null ? !id.equals(teamMatch.id) : teamMatch.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
