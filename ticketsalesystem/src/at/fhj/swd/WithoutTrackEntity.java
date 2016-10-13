package at.fhj.swd;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by NUC on 13.10.2016.
 */
@Entity
//@Table(name = "without_track", schema = "public", catalog = "haring")
public class WithoutTrackEntity extends StadiumEntity {
    private Integer fkStadiumId;

    @Basic
//    @Column(name = "fk_stadium_id")
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

        WithoutTrackEntity that = (WithoutTrackEntity) o;

        if (fkStadiumId != null ? !fkStadiumId.equals(that.fkStadiumId) : that.fkStadiumId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return fkStadiumId != null ? fkStadiumId.hashCode() : 0;
    }
}
