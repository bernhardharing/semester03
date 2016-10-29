package at.fhj.swd;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by NUC on 13.10.2016.
 */
public class TicketTest {

    private static Ticket ticket;
    private static Category category;
    private static Place place;

    final static String persistenceUnitName = "persistence";
    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    @BeforeClass
    public static void prepare(){
        factory = Persistence.createEntityManagerFactory( persistenceUnitName );
        manager = factory.createEntityManager();
        transaction = manager.getTransaction();

    }


    @AfterClass
    public static void teardown() {
        if (manager == null) return;
        manager.close();
        factory.close();
    }

    @Test public void create () {
        transaction.begin ();
        category = new Category("billig",10);
        manager.persist (category);

        place = new Place(category);
        manager.persist (place);

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        ticket = new Ticket(4,sqlDate,place);
        manager.persist (ticket);

        transaction.commit();

    }

    @Test public void delete() {
        transaction.begin ();
        manager.remove(ticket);
        manager.remove(place);
        manager.remove(category);
        transaction.commit();
    }
}