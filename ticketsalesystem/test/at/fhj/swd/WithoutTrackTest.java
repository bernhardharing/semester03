package at.fhj.swd;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Bogdan on 25.10.2016.
 */
public class WithoutTrackTest {
    private static WithoutTrack withoutTrack;

    final static String persistenceUnitName = "persistence";
    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    @BeforeClass
    public static void prepare(){
        withoutTrack = new WithoutTrack(1,"Arena",200);
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

    @Test
    public void create () {
        transaction.begin ();
        assertNotNull (withoutTrack);
        manager.persist (withoutTrack);
        transaction.commit();

    }

    @Test
    public void delete() {
        transaction.begin ();
        assertNotNull (withoutTrack);
        manager.remove(withoutTrack);
        transaction.commit();
    }
}
