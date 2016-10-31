package at.fhj.swd;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bernhard on 18.10.2016.
 */

public class TicketRepository {

    public TicketRepository(final EntityManager entityManager)
    {
        setEntityManager( entityManager );
    }

    private EntityManager entityManager;
    public void setEntityManager(final EntityManager entityManager)
    {
        if(entityManager == null)
            throw new IllegalArgumentException("EntityManager is invalid!");

        this.entityManager = entityManager;
    }


    public List<Ticket> findByTicketNumber(Integer ticketNumber)
    {
        TypedQuery<Ticket> query = entityManager.createNamedQuery("Ticket.findByTicketNumber", Ticket.class);
        query.setParameter("ticketNumber",ticketNumber);

        return query.getResultList();
    }
}
