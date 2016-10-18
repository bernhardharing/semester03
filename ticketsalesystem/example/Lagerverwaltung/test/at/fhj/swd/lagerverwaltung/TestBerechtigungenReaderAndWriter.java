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

public class TestBerechtigungenReaderAndWriter
{
	private static EntityManager managerSuperUser;
	private static EntityManager managerReaderAndWriter;
	private static EntityTransaction transaction;
	private static ArtikelstammRepository artikelstammRepository;
	
	private final String USER_READANDWRITE = "readandwrite";
	private final String PASSWORT_READANDWRITE = "readandwrite";
	private final String persistenceUnitName = "Lagerverwaltung";
	private Artikelstamm artikel = null;


	@Before
	public void setup() 
	{	
		Map<String, String> props = new HashMap<String, String>();
		props.put ("javax.persistence.jdbc.user", USER_READANDWRITE);
		props.put ("javax.persistence.jdbc.password", PASSWORT_READANDWRITE);
		
		managerSuperUser = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
		managerReaderAndWriter = Persistence.createEntityManagerFactory(persistenceUnitName, props).createEntityManager();
		
		artikelstammRepository = new ArtikelstammRepository(managerReaderAndWriter);
		
		//Testdaten anlegen
		transaction = managerSuperUser.getTransaction();
		transaction.begin();
		
	    //Artikel anlegen
		artikel = new Artikelstamm("Artikelnummer", "Bezeichnung");
		managerSuperUser.persist(artikel);
	    transaction.commit();
	}
	
	@Test
	public void testReaderAndWriterSelect()
	{
		//SELECT 
	    //Artikel NamedQuery FindAll
	    List<Artikelstamm> artikelListeFindAll = new ArrayList<Artikelstamm>();
	    artikelListeFindAll = artikelstammRepository.findAll();
	    Assert.assertEquals( artikelListeFindAll.size(), 1 );
	}
	
	@Test
	public void testReaderAndWriterInsert()
	{
		EntityTransaction transactionReaderAndWriter = managerReaderAndWriter.getTransaction();
		Artikelstamm artikel1 = null;
		
		//INSERT 
		transactionReaderAndWriter.begin();
		artikel1 = new Artikelstamm("Artikelnummer 1", "Bezeichnung 1");
		managerReaderAndWriter.persist(artikel1);
		transactionReaderAndWriter.commit();

		//Testdaten  wieder löschen
		transaction.begin();
		Artikelstamm artikelDummy = managerSuperUser.find(Artikelstamm.class, artikel1.getArtikelnummer());
		Assert.assertEquals( artikelDummy, artikel1);
		managerSuperUser.remove(artikelDummy);
		transaction.commit();
	}
	
	@Test
	public void testReaderAndWriterUpdate()
	{
		final String NEUE_BEZEICHNUNG = "Neue Bezeichnung";
		
		EntityTransaction transactionReaderAndWriter = managerReaderAndWriter.getTransaction();
		//UPDATE
		transactionReaderAndWriter = managerReaderAndWriter.getTransaction();
		transactionReaderAndWriter.begin();
		Artikelstamm artikelUpdate  = managerReaderAndWriter.find(Artikelstamm.class, artikel.getArtikelnummer());
		artikelUpdate.setBezeichnung( NEUE_BEZEICHNUNG );
		transactionReaderAndWriter.commit();
		
		Assert.assertEquals( managerReaderAndWriter.find(Artikelstamm.class, artikel.getArtikelnummer()).getBezeichnung(), NEUE_BEZEICHNUNG );
	}
	
	@Test
	public void testReaderDelete()
	{
		EntityTransaction transactionReaderAndWriter = null;
		//INSERT
		try
		{
			transactionReaderAndWriter = managerReaderAndWriter.getTransaction();
			transactionReaderAndWriter.begin();
			managerReaderAndWriter.remove(managerReaderAndWriter.find(Artikelstamm.class, artikel.getArtikelnummer()));
			transactionReaderAndWriter.commit();
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
		managerReaderAndWriter.close();
	}
}
