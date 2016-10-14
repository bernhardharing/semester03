package at.fhj.swd;

import javax.persistence.*;

/**
 * Created by NUC on 14.10.2016.
 */
@Entity
@Table(name = "without_track", schema = "public", catalog = "haring")
public class WithoutTrack {
    private Integer pkId;
    private String location;
    private String totalCapacity;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WithoutTrack that = (WithoutTrack) o;

        if (pkId != null ? !pkId.equals(that.pkId) : that.pkId != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (totalCapacity != null ? !totalCapacity.equals(that.totalCapacity) : that.totalCapacity != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pkId != null ? pkId.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (totalCapacity != null ? totalCapacity.hashCode() : 0);
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
