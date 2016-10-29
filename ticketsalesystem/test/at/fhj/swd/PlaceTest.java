package at.fhj.swd;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Bogdan on 25.10.2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlaceTest {
    private static Place place;
    private static Category category;

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

    @Test
    public void A_create () {
        transaction.begin ();
        category = new Category("billig",10);
        manager.persist (category);

        place = new Place(category);
        manager.persist (place);

        transaction.commit();



    }

    @Test public void B_delete() {
        transaction.begin ();
        manager.remove(place);
        manager.remove(category);
        transaction.commit();
    }
}
