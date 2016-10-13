package at.fhj.swd;

import javax.persistence.*;

/**
 * Created by NUC on 13.10.2016.
 */
@Entity
@Table(name = "place", schema = "public", catalog = "haring")
@IdClass(PlaceEntityPK.class)
public class PlaceEntity {
    private Integer placeNumber;
    private Integer placeRow;
    private Integer placeSection;
    private String fkCategoryDescription;
    private Integer fkStadionId;
    private Integer fkTicketNumber;

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

    @Basic
    @Column(name = "fk_category_description")
    public String getFkCategoryDescription() {
        return fkCategoryDescription;
    }

    public void setFkCategoryDescription(String fkCategoryDescription) {
        this.fkCategoryDescription = fkCategoryDescription;
    }

    @Basic
    @Column(name = "fk_stadion_id")
    public Integer getFkStadionId() {
        return fkStadionId;
    }

    public void setFkStadionId(Integer fkStadionId) {
        this.fkStadionId = fkStadionId;
    }

    @Basic
    @Column(name = "fk_ticket_number")
    public Integer getFkTicketNumber() {
        return fkTicketNumber;
    }

    public void setFkTicketNumber(Integer fkTicketNumber) {
        this.fkTicketNumber = fkTicketNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaceEntity that = (PlaceEntity) o;

        if (placeNumber != null ? !placeNumber.equals(that.placeNumber) : that.placeNumber != null) return false;
        if (placeRow != null ? !placeRow.equals(that.placeRow) : that.placeRow != null) return false;
        if (placeSection != null ? !placeSection.equals(that.placeSection) : that.placeSection != null) return false;
        if (fkCategoryDescription != null ? !fkCategoryDescription.equals(that.fkCategoryDescription) : that.fkCategoryDescription != null)
            return false;
        if (fkStadionId != null ? !fkStadionId.equals(that.fkStadionId) : that.fkStadionId != null) return false;
        if (fkTicketNumber != null ? !fkTicketNumber.equals(that.fkTicketNumber) : that.fkTicketNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = placeNumber != null ? placeNumber.hashCode() : 0;
        result = 31 * result + (placeRow != null ? placeRow.hashCode() : 0);
        result = 31 * result + (placeSection != null ? placeSection.hashCode() : 0);
        result = 31 * result + (fkCategoryDescription != null ? fkCategoryDescription.hashCode() : 0);
        result = 31 * result + (fkStadionId != null ? fkStadionId.hashCode() : 0);
        result = 31 * result + (fkTicketNumber != null ? fkTicketNumber.hashCode() : 0);
        return result;
    }
}
