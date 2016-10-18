package at.fhj.swd;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by bernhard on 18.10.2016.
 */
@Entity
public class Stadium {
    private Integer id;
    private String location;
    private String totalCapacity;

    @Id
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

        Stadium stadium = (Stadium) o;

        if (id != null ? !id.equals(stadium.id) : stadium.id != null) return false;
        if (location != null ? !location.equals(stadium.location) : stadium.location != null) return false;
        if (totalCapacity != null ? !totalCapacity.equals(stadium.totalCapacity) : stadium.totalCapacity != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (totalCapacity != null ? totalCapacity.hashCode() : 0);
        return result;
    }
}
