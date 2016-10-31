package at.fhj.swd;

import javax.persistence.*;

/**
 * Created by NUC on 29.10.2016.
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "Stadium.findAll",
                query = "SELECT c FROM Stadium c"),
})
public class Stadium {
    @Id @Column(name = "id")
    private Integer id;

    @Column(name = "location")
    private String location;

    @Column(name = "total_capacity")
    private Integer totalCapacity;

    public Stadium(Integer id, String location, Integer totalCapacity) {
        setId(id);
        setLocation(location);
        setTotalCapacity(totalCapacity);
    }

    public Stadium() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public Integer getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(Integer totalCapacity) {
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
