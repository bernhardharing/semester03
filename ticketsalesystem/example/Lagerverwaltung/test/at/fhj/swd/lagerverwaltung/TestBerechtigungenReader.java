package at.fhj.swd.lagerverwaltung;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestBerechtigungenReader
{
	private static EntityManager managerSuperUser;
	private static EntityManager managerReader;
	private static EntityTransaction transaction;
	private static ArtikelstammRepository artikelstammRepository;
	
	private final String USER_READER = "reader";
	private final String PASSWORT_READER = "reader";
	private final String persistenceUnitName = "Lagerverwaltung";
	private Artikelstamm artikel = null;


	@Before
	public void setup() 
	{	
		Map<String, String> props = new HashMap<String, String>();
		props.put ("javax.persistence.jdbc.user", USER_READER);
		props.put ("javax.persistence.jdbc.password", PASSWORT_READER);
		
		managerSuperUser = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
		managerReader = Persistence.createEntityManagerFactory(persistenceUnitName, props).createEntityManager();
		
		artikelstammRepository = new ArtikelstammRepository(managerReader);
		
		//Testdaten anlegen
		transaction = managerSuperUser.getTransaction();
		transaction.begin();
		
	    //Artikel anlegen
		artikel = new Artikelstamm("Artikelnummer", "Bezeichnung");
		managerSuperUser.persist(artikel);
	    transaction.commit();
	}
	
	@Test
	public void testReaderSelect()
	{
		//SELECT 
	    //Artikel NamedQuery FindAll
	    List<Artikelstamm> artikelListeFindAll = new ArrayList<Artikelstamm>();
	    artikelListeFindAll = artikelstammRepository.findAll();
	    Assert.assertEquals( artikelListeFindAll.size(), 1 );
	}
	
	@Test
	public void testReaderInsert()
	{
		EntityTransaction transactionReader = null;
		//INSERT 
		try
		{
			transactionReader = managerReader.getTransaction();
			transactionReader.begin();
			managerReader.persist(new Artikelstamm("Artikelnummer 1", "Bezeichnung 1"));
			transactionReader.commit();
		    Assert.fail();
		}
		catch(Exception e)
		{
			Assert.assertTrue(e.getMessage().contains( "permission denied" ));
		}	
	}
	
	
	
	@Test
	public void testReaderUpdate()
	{
		EntityTransaction transactionReader = null;
		//INSERT
		try
		{
			transactionReader = managerReader.getTransaction();
			transactionReader.begin();
			artikel.setBezeichnung( "Neue Bezeichnung" );
			managerReader.persist(artikel);
			transactionReader.commit();
		    Assert.fail();
		}
		catch(Exception e)
		{
			Assert.assertTrue(e.getMessage().contains( "permission denied" ));
		}	
	}
	
	@Test
	public void testReaderDelete()
	{
		EntityTransaction transactionReader = null;
		//INSERT
		try
		{
			transactionReader = managerReader.getTransaction();
			transactionReader.begin();
			managerReader.remove(managerReader.find(Artikelstamm.class, artikel.getArtikelnummer()));
			transactionReader.commit();
		    Assert.fail();
		}
		catch(Exception e)
		{
			Assert.assertTrue(e.getMessage().contains( "permission denied" ));
		}	
	}
	
	@After
	public void teardown()
	{
		//Testdaten löschen
		transaction.begin();
		managerSuperUser.remove( artikel );
		transaction.commit();
		
		managerSuperUser.close();
		managerReader.close();
	}
}
