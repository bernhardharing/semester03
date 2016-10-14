package at.fhj.swd;

import javax.persistence.*;

/**
 * Created by NUC on 14.10.2016.
 */
@Entity
@Table(name = "with_track", schema = "public", catalog = "haring")
public class WithTrack {
    private Integer pkId;
    private String location;
    private String totalCapacity;
    private Integer numberTracks;

    @Basic
    @Column(name = "pk_id")
    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "total_capacity")
    public String getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(String totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    @Basic
    @Column(name = "number_tracks")
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

        WithTrack withTrack = (WithTrack) o;

        if (pkId != null ? !pkId.equals(withTrack.pkId) : withTrack.pkId != null) return false;
        if (location != null ? !location.equals(withTrack.location) : withTrack.location != null) return false;
        if (totalCapacity != null ? !totalCapacity.equals(withTrack.totalCapacity) : withTrack.totalCapacity != null)
            return false;
        if (numberTracks != null ? !numberTracks.equals(withTrack.numberTracks) : withTrack.numberTracks != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pkId != null ? pkId.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (totalCapacity != null ? totalCapacity.hashCode() : 0);
        result = 31 * result + (numberTracks != null ? numberTracks.hashCode() : 0);
        return result;
    }
    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
