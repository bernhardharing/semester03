package at.fhj.swd;

import org.junit.AfterClass;
import org.junit.BeforeClass;
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
public class StadiumTest {
    private static Stadium stadium;

    final static String persistenceUnitName = "persistence";
    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    @BeforeClass
    public static void prepare(){
        stadium = new Stadium(1,"Arena",200);
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
        assertNotNull (stadium);
        manager.persist (stadium);
        transaction.commit();
        StadiumRepository stadiumRepository = new StadiumRepository(manager);

        List<Stadium> list = stadiumRepository.findAll();
        for (int i=0; i< list.size();i++){
            Stadium stadium = list.get(0);
            System.out.println(stadium.getLocation() + stadium.getTotalCapacity());
        }
    }

    @Test public void delete() {
        transaction.begin ();
        assertNotNull (stadium);
        manager.remove(stadium);
        transaction.commit();
    }
}
