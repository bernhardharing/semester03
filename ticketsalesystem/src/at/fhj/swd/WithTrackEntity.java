package at.fhj.swd;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by NUC on 13.10.2016.
 */
@Entity
//@Table(name = "with_track", schema = "public", catalog = "haring")
public class WithTrackEntity extends StadiumEntity{
    private Integer fkStadiumId;
    private Integer numberTracks;

    @Basic
//    @Column(name = "fk_stadium_id")
    public Integer getFkStadiumId() {
        return fkStadiumId;
    }

    public void setFkStadiumId(Integer fkStadiumId) {
        this.fkStadiumId = fkStadiumId;
    }

    @Basic
//    @Column(name = "number_tracks")
    public Integer getNumberTracks() {
        return numberTracks;
    }

    public void setNumberTracks(Integer numberTracks) {
        this.numberTracks = numberTracks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WithTrackEntity that = (WithTrackEntity) o;

        if (fkStadiumId != null ? !fkStadiumId.equals(that.fkStadiumId) : that.fkStadiumId != null) return false;
        if (numberTracks != null ? !numberTracks.equals(that.numberTracks) : that.numberTracks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fkStadiumId != null ? fkStadiumId.hashCode() : 0;
        result = 31 * result + (numberTracks != null ? numberTracks.hashCode() : 0);
        return result;
    }
}
