package at.fhj.swd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by NUC on 14.10.2016.
 */
@Entity
@Table(name = "team_match", schema = "public", catalog = "haring")
public class TeamMatch {
    private Integer pkId;

    @Id
    @Column(name = "pk_id")
    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamMatch teamMatch = (TeamMatch) o;

        if (pkId != null ? !pkId.equals(teamMatch.pkId) : teamMatch.pkId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return pkId != null ? pkId.hashCode() : 0;
    }
}
