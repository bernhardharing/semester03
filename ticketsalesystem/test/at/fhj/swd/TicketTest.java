package at.fhj.swd;

import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by NUC on 13.10.2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TicketTest {

    private static Ticket ticket;
    private static Category category;
    private static Place place;
    private static Customer customer;
    private static Match match;
    private  static Contingent contingent;

    final static String persistenceUnitName = "persistence";
    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    @BeforeClass
    public static void prepare(){
        factory = Persistence.createEntityManagerFactory( persistenceUnitName );
        manager = factory.createEntityManager();
        transaction = manager.getTransaction();

        category = new Category("billig",10);

        customer = new Customer("Bogdan","Teststraße 2");
        place = new Place(category);

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        Time time = new Time(123300L);
        match = new  Match(sqlDate,time);

        contingent = new Contingent(20000,match);


        ticket = new Ticket(sqlDate,place,customer,match,contingent);
    }


    @AfterClass
    public static void teardown() {
        if (manager == null) return;
        manager.close();
        factory.close();
    }

    @Test public void A_create () {
        transaction.begin ();

        manager.persist (category);
        manager.persist (place);
        manager.persist (customer);
        manager.persist(match);
        manager.persist(contingent);
        manager.persist (ticket);


        transaction.commit();

    }

    @Test public void B_repositoryTest(){
        TicketRepository ticketRepository = new TicketRepository(manager);

        List <Ticket> tickets = ticketRepository.findByTicketNumber(ticket.getTicketNumber());
        Assert.assertTrue(tickets.size()>0);
    }

    @Test public void C_delete() {
        transaction.begin ();
        manager.remove(ticket);
        manager.remove(customer);
        manager.remove(place);
        manager.remove(category);
        manager.remove(match);
        manager.remove(contingent);
        transaction.commit();
    }
}