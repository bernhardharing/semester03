package at.fhj.swd;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by bernhard on 18.10.2016.
 */
@Entity
public class Ticket {
    private Integer ticketNumber;
    private Date date;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (ticketNumber != null ? !ticketNumber.equals(ticket.ticketNumber) : ticket.ticketNumber != null)
            return false;
        if (date != null ? !date.equals(ticket.date) : ticket.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ticketNumber != null ? ticketNumber.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
