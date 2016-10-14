package at.fhj.swd;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by NUC on 14.10.2016.
 */
@Entity
@Table(name = "without_track", schema = "public", catalog = "haring")
public class WithoutTrack {
    private Integer fkStadiumId;

    @Basic
    @Column(name = "fk_stadium_id")
    public Integer getFkStadiumId() {
        return fkStadiumId;
    }

    public void setFkStadiumId(Integer fkStadiumId) {
        this.fkStadiumId = fkStadiumId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WithoutTrack that = (WithoutTrack) o;

        if (fkStadiumId != null ? !fkStadiumId.equals(that.fkStadiumId) : that.fkStadiumId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return fkStadiumId != null ? fkStadiumId.hashCode() : 0;
    }

    private String id;

    @javax.persistence.Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
