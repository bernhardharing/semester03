package at.fhj.swd;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by NUC on 29.10.2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Ticket.findByTicketNumber",
                query = "SELECT t FROM Ticket t WHERE t.ticketNumber = :ticketNumber")
})
public class Ticket {
    @SequenceGenerator( name="TicketIdGenerator",
            sequenceName="Ticket_Sequence",
            allocationSize = 1)

    @GeneratedValue(generator="TicketIdGenerator")
    @Id @Column(name = "ticket_number")
    private Integer ticketNumber;
    @Column(name = "date")
    private Date date;

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @OneToOne
    @JoinColumn(name="fk_place_id")
    private Place place;

    @ManyToOne
    @JoinColumn(name="fk_customer_number")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="fk_match_id")
    private Match match;

    @ManyToOne
    @JoinColumn(name="fk_contingent_id")
    private Contingent contingent;

    public Ticket(Date date, Place place,Customer customer, Match match, Contingent contingent) {
        this.date = date;
        this.place = place;
        this.customer = customer;
        this.match = match;
        this.contingent = contingent;
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
