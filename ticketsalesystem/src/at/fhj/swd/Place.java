package at.fhj.swd;

import javax.persistence.*;

/**
 * Created by NUC on 29.10.2016.
 */
@Entity
public class Place {
    @Id
    private Integer id;
    private Integer placeId;

    @ManyToOne
    private Category category;

    @OneToOne
    private Ticket ticket;


    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "place_id")
    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        if (id != null ? !id.equals(place.id) : place.id != null) return false;
        if (placeId != null ? !placeId.equals(place.placeId) : place.placeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (placeId != null ? placeId.hashCode() : 0);
        return result;
    }
}
