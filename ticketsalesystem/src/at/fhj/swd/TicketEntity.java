package at.fhj.swd;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by NUC on 13.10.2016.
 */
@Entity
@Table(name = "ticket", schema = "public", catalog = "haring")
public class TicketEntity {
    private Integer ticketNumber;
    private Date date;
    private String fkCustomerNumber;
    private Integer fkContingentId;
    private Integer fkPlaceNumber;
    private Integer fkPlaceRow;
    private Integer fkPlaceSection;

    @Id
    @Column(name = "ticket_number")
    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Integer ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "fk_customer_number")
    public String getFkCustomerNumber() {
        return fkCustomerNumber;
    }

    public void setFkCustomerNumber(String fkCustomerNumber) {
        this.fkCustomerNumber = fkCustomerNumber;
    }

    @Basic
    @Column(name = "fk_contingent_id")
    public Integer getFkContingentId() {
        return fkContingentId;
    }

    public void setFkContingentId(Integer fkContingentId) {
        this.fkContingentId = fkContingentId;
    }

    @Basic
    @Column(name = "fk_place_number")
    public Integer getFkPlaceNumber() {
        return fkPlaceNumber;
    }

    public void setFkPlaceNumber(Integer fkPlaceNumber) {
        this.fkPlaceNumber = fkPlaceNumber;
    }

    @Basic
    @Column(name = "fk_place_row")
    public Integer getFkPlaceRow() {
        return fkPlaceRow;
    }

    public void setFkPlaceRow(Integer fkPlaceRow) {
        this.fkPlaceRow = fkPlaceRow;
    }

    @Basic
    @Column(name = "fk_place_section")
    public Integer getFkPlaceSection() {
        return fkPlaceSection;
    }

    public void setFkPlaceSection(Integer fkPlaceSection) {
        this.fkPlaceSection = fkPlaceSection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketEntity that = (TicketEntity) o;

        if (ticketNumber != null ? !ticketNumber.equals(that.ticketNumber) : that.ticketNumber != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (fkCustomerNumber != null ? !fkCustomerNumber.equals(that.fkCustomerNumber) : that.fkCustomerNumber != null)
            return false;
        if (fkContingentId != null ? !fkContingentId.equals(that.fkContingentId) : that.fkContingentId != null)
            return false;
        if (fkPlaceNumber != null ? !fkPlaceNumber.equals(that.fkPlaceNumber) : that.fkPlaceNumber != null)
            return false;
        if (fkPlaceRow != null ? !fkPlaceRow.equals(that.fkPlaceRow) : that.fkPlaceRow != null) return false;
        if (fkPlaceSection != null ? !fkPlaceSection.equals(that.fkPlaceSection) : that.fkPlaceSection != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ticketNumber != null ? ticketNumber.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (fkCustomerNumber != null ? fkCustomerNumber.hashCode() : 0);
        result = 31 * result + (fkContingentId != null ? fkContingentId.hashCode() : 0);
        result = 31 * result + (fkPlaceNumber != null ? fkPlaceNumber.hashCode() : 0);
        result = 31 * result + (fkPlaceRow != null ? fkPlaceRow.hashCode() : 0);
        result = 31 * result + (fkPlaceSection != null ? fkPlaceSection.hashCode() : 0);
        return result;
    }
}
