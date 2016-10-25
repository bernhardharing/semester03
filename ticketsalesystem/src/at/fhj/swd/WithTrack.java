package at.fhj.swd;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;

/**
 * Created by bernhard on 18.10.2016.
 */
@Entity
@Table(name = "with_track", schema = "public", catalog = "haring")
public class WithTrack {
    @Id
    private Integer id;
    private String location;
    private Integer totalCapacity;
    private Integer numberTracks;

    @Basic
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    public Integer getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(Integer totalCapacity) {
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

        if (id != null ? !id.equals(withTrack.id) : withTrack.id != null) return false;
        if (location != null ? !location.equals(withTrack.location) : withTrack.location != null) return false;
        if (totalCapacity != null ? !totalCapacity.equals(withTrack.totalCapacity) : withTrack.totalCapacity != null)
            return false;
        if (numberTracks != null ? !numberTracks.equals(withTrack.numberTracks) : withTrack.numberTracks != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (totalCapacity != null ? totalCapacity.hashCode() : 0);
        result = 31 * result + (numberTracks != null ? numberTracks.hashCode() : 0);
        return result;
    }
}
