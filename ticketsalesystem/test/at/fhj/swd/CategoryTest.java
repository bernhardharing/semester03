package at.fhj.swd;

import com.sun.org.apache.xerces.internal.xs.StringList;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by NUC on 13.10.2016.
 */
public class CategoryTest {

    private static Category category;

    final static String persistenceUnitName = "persistence";
    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    @BeforeClass
    public static void prepare(){
        category = new Category("test",30);
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
        assertNotNull (category);
        manager.persist (category);
        transaction.commit();
        CategoryRepository categoryRepository = new CategoryRepository(manager);

        List<Category> list = categoryRepository.findAll();
        for (int i=0; i< list.size();i++){
            Category category = list.get(0);
            System.out.println(category.getDescription() + category.getPrice());
        }
    }

    @Test public void delete() {
        transaction.begin ();
        assertNotNull (category);
        manager.remove(category);
        transaction.commit();
    }
}