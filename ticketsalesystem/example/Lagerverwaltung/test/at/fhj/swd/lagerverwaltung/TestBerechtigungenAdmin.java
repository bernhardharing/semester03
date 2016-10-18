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

public class TestBerechtigungenAdmin
{
	private static EntityManager managerAdmin;
	private static EntityTransaction transaction;
	private static ArtikelstammRepository artikelstammRepository;
	
	private final String USER_ADMIN = "admin";
	private final String PASSWORT_ADMIN = "admin";
	private final String persistenceUnitName = "Lagerverwaltung";
	private Artikelstamm artikel = null;


	@Before
	public void setup() 
	{	
		Map<String, String> props = new HashMap<String, String>();
		props.put ("javax.persistence.jdbc.user", USER_ADMIN);
		props.put ("javax.persistence.jdbc.password", PASSWORT_ADMIN);
		
		managerAdmin = Persistence.createEntityManagerFactory(persistenceUnitName, props).createEntityManager();
		
		artikelstammRepository = new ArtikelstammRepository(managerAdmin);
		
		//Testdaten anlegen
		transaction = managerAdmin.getTransaction();
		transaction.begin();
		
	    //Artikel anlegen
		artikel = new Artikelstamm("Artikelnummer", "Bezeichnung");
		managerAdmin.persist(artikel);
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
		Artikelstamm artikel1 = null;
		
		//INSERT 
		transaction.begin();
		artikel1 = new Artikelstamm("Artikelnummer 1", "Bezeichnung 1");
		managerAdmin.persist(artikel1);
		transaction.commit();

		//Testdaten  wieder löschen
		transaction.begin();
		Artikelstamm artikelDummy = managerAdmin.find(Artikelstamm.class, artikel1.getArtikelnummer());
		Assert.assertEquals( artikelDummy, artikel1);
		managerAdmin.remove(artikelDummy);
		transaction.commit();
	}
	
	@Test
	public void testReaderAndWriterUpdate()
	{
		final String NEUE_BEZEICHNUNG = "Neue Bezeichnung";
		
		//UPDATE
		transaction = managerAdmin.getTransaction();
		transaction.begin();
		Artikelstamm artikelUpdate  = managerAdmin.find(Artikelstamm.class, artikel.getArtikelnummer());
		artikelUpdate.setBezeichnung( NEUE_BEZEICHNUNG );
		transaction.commit();
		
		Assert.assertEquals( managerAdmin.find(Artikelstamm.class, artikel.getArtikelnummer()).getBezeichnung(), NEUE_BEZEICHNUNG );
	}
	
	@Test
	public void testReaderDelete()
	{
		//DELETE
		transaction = managerAdmin.getTransaction();
		transaction.begin();
		managerAdmin.remove(managerAdmin.find(Artikelstamm.class, artikel.getArtikelnummer()));
		transaction.commit();
		
		//Es darf kein Artikel existieren
	    //Artikel NamedQuery FindAll
	    List<Artikelstamm> artikelListeFindAll = new ArrayList<Artikelstamm>();
	    artikelListeFindAll = artikelstammRepository.findAll();
	    Assert.assertEquals( artikelListeFindAll.size(), 0);
	}
	
	@After
	public void teardown()
	{
		//Testdaten löschen
		transaction.begin();
		if(managerAdmin.find( Artikelstamm.class, artikel.getArtikelnummer()) != null)
			managerAdmin.remove( artikel );
		transaction.commit();
		
		managerAdmin.close();
	}
}
