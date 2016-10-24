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
 * Created by Bogdan on 24.10.2016.
 */
public class CustomerTest {

    private static Customer customer;

    final static String persistenceUnitName = "persistence";
    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    @BeforeClass
    public static void prepare(){
        customer = new Customer(1,"Bogdan","Teststraße 2");
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
        assertNotNull (customer);
        manager.persist (customer);
        transaction.commit();
        CustomerRepository customerRepository = new CustomerRepository(manager);

        List<Customer> list = customerRepository.findAll();
        for (int i=0; i< list.size();i++){
            Customer customer = list.get(0);
            System.out.println(customer.getName() + customer.getAddress());
        }
    }

}
