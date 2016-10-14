package at.fhj.swd;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by NUC on 14.10.2016.
 */
public class PlacePK implements Serializable {
    private Integer placeNumber;
    private Integer placeRow;
    private Integer placeSection;

    @Column(name = "place_number")
    @Id
    public Integer getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(Integer placeNumber) {
        this.placeNumber = placeNumber;
    }

    @Column(name = "place_row")
    @Id
    public Integer getPlaceRow() {
        return placeRow;
    }

    public void setPlaceRow(Integer placeRow) {
        this.placeRow = placeRow;
    }

    @Column(name = "place_section")
    @Id
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

        PlacePK placePK = (PlacePK) o;

        if (placeNumber != null ? !placeNumber.equals(placePK.placeNumber) : placePK.placeNumber != null) return false;
        if (placeRow != null ? !placeRow.equals(placePK.placeRow) : placePK.placeRow != null) return false;
        if (placeSection != null ? !placeSection.equals(placePK.placeSection) : placePK.placeSection != null)
            return false;

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
