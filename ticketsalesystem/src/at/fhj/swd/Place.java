package at.fhj.swd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Created by bernhard on 18.10.2016.
 */
@Entity
public class Place {
    private Integer placeNumber;
    private Integer placeRow;
    private Integer placeSection;

    @Id
    @Column(name = "place_number")
    public Integer getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(Integer placeNumber) {
        this.placeNumber = placeNumber;
    }

    @Id
    @Column(name = "place_row")
    public Integer getPlaceRow() {
        return placeRow;
    }

    public void setPlaceRow(Integer placeRow) {
        this.placeRow = placeRow;
    }

    @Id
    @Column(name = "place_section")
    public Integer getPlaceSection() {
        return placeSection;
    }

    public void setPlaceSection(Integer placeSection) {
        this.placeSection = placeSection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        if (placeNumber != null ? !placeNumber.equals(place.placeNumber) : place.placeNumber != null) return false;
        if (placeRow != null ? !placeRow.equals(place.placeRow) : place.placeRow != null) return false;
        if (placeSection != null ? !placeSection.equals(place.placeSection) : place.placeSection != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = placeNumber != null ? placeNumber.hashCode() : 0;
        result = 31 * result + (placeRow != null ? placeRow.hashCode() : 0);
        result = 31 * result + (placeSection != null ? placeSection.hashCode() : 0);
        return result;
    }
}
