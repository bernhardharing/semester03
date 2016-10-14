package at.fhj.swd;

import javax.persistence.*;

/**
 * Created by NUC on 14.10.2016.
 */

public class WithTrack {
    private Integer fkStadiumId;
    @Id
    private Integer numberTracks;

    @Basic
    public Integer getFkStadiumId() {
        return fkStadiumId;
    }

    public void setFkStadiumId(Integer fkStadiumId) {
        this.fkStadiumId = fkStadiumId;
    }


    public void setNumberTracks(Integer numberTracks) {
        this.numberTracks = numberTracks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WithTrack withTrack = (WithTrack) o;

        if (fkStadiumId != null ? !fkStadiumId.equals(withTrack.fkStadiumId) : withTrack.fkStadiumId != null)
            return false;
        if (numberTracks != null ? !numberTracks.equals(withTrack.numberTracks) : withTrack.numberTracks != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fkStadiumId != null ? fkStadiumId.hashCode() : 0;
        result = 31 * result + (numberTracks != null ? numberTracks.hashCode() : 0);
        return result;
    }

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
