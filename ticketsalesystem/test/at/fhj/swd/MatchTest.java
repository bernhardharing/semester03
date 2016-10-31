package at.fhj.swd;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Time;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by NUC on 13.10.2016.
 */
public class MatchTest {

    private static Team teamA;
    private static Team teamB;
    private static Match match;

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
        teamA=new Team(3,"Lask");
        teamB =new Team(4,"Schalke");

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        Time time = new Time(123300L);
        match = new  Match(sqlDate,time);


        transaction.begin ();
        //todo: get this 2 teams from db via id
        manager.persist (teamA);
        manager.persist (teamB);
        match.setTeams(teamA);
        match.setTeams(teamB);
        manager.persist (match);

        transaction.commit();

    }

    @Test public void delete() {
        transaction.begin ();

        transaction.commit();
    }
}