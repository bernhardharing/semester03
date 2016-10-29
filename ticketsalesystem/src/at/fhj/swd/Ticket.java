package at.fhj.swd;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by NUC on 29.10.2016.
 */
@Entity
public class Ticket {
    @Id @Column(name = "ticket_number")
    private Integer ticketNumber;
    @Column(name = "date")
    private Date date;

    @OneToOne
    @JoinColumn(name="fk_place_id")
    private Place place;

    @ManyToOne
    @JoinColumn(name="fk_customer_number")
    private Customer customer;

    public Ticket(Integer ticketNumber, Date date, Place place,Customer customer) {
        this.ticketNumber = ticketNumber;
        this.date = date;
        this.place = place;
        this.customer = customer;
    }

    public Ticket() {
    }


    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Integer ticketNumber) {
        this.ticketNumber = ticketNumber;
    }



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
