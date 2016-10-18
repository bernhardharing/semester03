package at.fhj.swd.lagerverwaltung;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fhj.swd.lagerverwaltung.Artikelstamm;
import at.fhj.swd.lagerverwaltung.Bestand;

public class TestArtikelBestandCharge 
{	
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	private static ArtikelstammRepository artikelstammRepository;
	private static ChargeRepository chargeRepository;
	private static BestandRepository bestandRepository;
	
	private final String persistenceUnitName = "Lagerverwaltung";
	private final String ARTIKELNUMMER_1 = "1";
	private final String ARTIKELNUMMER_2 = "2";
	private final String ARTIKELNUMMER_3 = "3";
	private final String ARTIKELBEZEICHNUNG_1 = "Artikel 1";
	private final String ARTIKELBEZEICHNUNG_2 = "Artikel 2";
	private final int BESTAND_MENGE_4 = 4;
	
	@Before
	public void setup() 
	{	
		factory = Persistence.createEntityManagerFactory(persistenceUnitName);
		manager = factory.createEntityManager();
		artikelstammRepository = new ArtikelstammRepository(manager);
		chargeRepository = new ChargeRepository(manager);
		bestandRepository = new BestandRepository(manager);
	}
	
	@Test
	public void testPersist() 
	{		
		//Test Insert
		//==========================================================================
		transaction = manager.getTransaction();
		transaction.begin();
		
		//Artikel 1
		Artikelstamm artikel1 = new Artikelstamm(ARTIKELNUMMER_1, ARTIKELBEZEICHNUNG_1);
		
		manager.persist(artikel1);
		
		//Artikel 2
		Artikelstamm artikel2 = new Artikelstamm(ARTIKELNUMMER_2, ARTIKELBEZEICHNUNG_2);
		manager.persist(artikel2);
		
		//Artikel 3
		Artikelstamm artikel3 = new Artikelstamm(ARTIKELNUMMER_3, ARTIKELBEZEICHNUNG_2);
		manager.persist(artikel3);
	    
	    //Bestand 1
	    Bestand bestand1 = new Bestand(1, artikel1);
	    manager.persist(bestand1);
	    
	    //Bestand 2 
	    Bestand bestand2 = new Bestand(2, artikel1);
	    manager.persist(bestand2);
	    
	    //Charge 1
	    Charge charge1 = new Charge("Charge1");
	    artikel1.addCharge(charge1);
	    manager.persist(charge1);
	    
	    //Charge 2
	    Charge charge2 = new Charge("Charge2");
	    artikel1.addCharge(charge2);
	    artikel2.addCharge(charge2);
	    manager.persist(charge2);
	    
		transaction.commit();
		
		//Artikel 1
		Artikelstamm artikel1Dummy = manager.find(Artikelstamm.class, ARTIKELNUMMER_1);
	    Assert.assertTrue(artikel1.equals(artikel1Dummy));
	    
	    //NamedQuery getSingleResult with one Result
	    Assert.assertTrue(artikelstammRepository.findByBezeichnung( ARTIKELBEZEICHNUNG_1 ).equals( artikel1Dummy ));
	    
	    //NamedQuery getSingleResult with no Result
	    try
	    {
	    	artikelstammRepository.findByBezeichnung( "NON EXISTING" );
	    	Assert.fail();
	    }
	    catch(NoResultException e)
	    {
	    	//OK
	    }
	    
	    //NamedQuery getSingleResult with more Results
	    try
	    {
	    	artikelstammRepository.findByBezeichnung(ARTIKELBEZEICHNUNG_2);
	    	Assert.fail();
	    }
	    catch(NonUniqueResultException e)
	    {
	    	//OK
	    }
	    
	    
	    //Asocciation Artikel 1 --[*]-> Bestand
	    Assert.assertEquals(artikel1Dummy.getBestaende().size(), 2);
	    Assert.assertTrue(artikel1Dummy.getBestaende().contains(bestand1));
	    Assert.assertTrue(artikel1Dummy.getBestaende().contains(bestand2));
	    
	    //NamedQuery findBestaende
	    List<ArtikelBestand> artikelBestandListe = new ArrayList<ArtikelBestand>();
	    artikelBestandListe = artikelstammRepository.findBestaende( artikel1Dummy.getArtikelnummer() );
	    Assert.assertEquals(artikelBestandListe.size(), 2);
	    //ArtikelBestand 1
	    ArtikelBestand artikelBestand1 = new ArtikelBestand(artikel1Dummy.getArtikelnummer(), 
	    		                                            artikel1Dummy.getBezeichnung(),
	    		                                            bestand1.getBestandsid(),
	    		                                            bestand1.getMenge());
	    
	    Assert.assertTrue( artikelBestandListe.contains(artikelBestand1));
	    //Bestand 2
	    ArtikelBestand artikelBestand2 = new ArtikelBestand(artikel1Dummy.getArtikelnummer(), 
	    		                                            artikel1Dummy.getBezeichnung(),
	    		                                            bestand2.getBestandsid(),
	    		                                            bestand2.getMenge());
	    Assert.assertTrue( artikelBestandListe.contains(artikelBestand2));	    
	    
	    //Asocciation Artikel 1 --[*]-> Charge
	    Assert.assertEquals(artikel1Dummy.getChargen().size(), 2);
	    Assert.assertTrue(artikel1Dummy.getChargen().contains(charge1));
	    Assert.assertTrue(artikel1Dummy.getChargen().contains(charge2));	
	    
		//Artikel 2
		Artikelstamm artikel2Dummy = manager.find(Artikelstamm.class, ARTIKELNUMMER_2);
	    Assert.assertTrue(artikel2.equals(artikel2Dummy));
	    //Asocciation Artikel 2 --[*]-> Bestand
	    Assert.assertEquals(artikel2Dummy.getBestaende().size(), 0);
	    //Asocciation Artikel 2 --[*]-> Charge
	    Assert.assertEquals(artikel2Dummy.getChargen().size(), 1);
	    Assert.assertTrue(artikel2Dummy.getChargen().contains(charge2));
	    
	    //Artikel NamedQuery FindAll
	    List<Artikelstamm> artikelListeFindAll = new ArrayList<Artikelstamm>();
	    artikelListeFindAll = artikelstammRepository.findAll();
	    Assert.assertEquals( artikelListeFindAll.size(), 3 );
	    Assert.assertTrue( artikelListeFindAll.contains( artikel1 ) );
	    Assert.assertTrue( artikelListeFindAll.contains( artikel2 ) );
	    
	    
	    //Bestand 1
	    Bestand bestand1Dummy = manager.find(Bestand.class, bestand1.getBestandsid());
	    Assert.assertTrue(bestand1.equals(bestand1Dummy));
	    //Asocciation Artikel --[*]-> Bestand
	    Assert.assertTrue(bestand1Dummy.getArtikel().equals(artikel1Dummy));
	    
	    //Bestand 2
	    Bestand bestand2Dummy = manager.find(Bestand.class, bestand2.getBestandsid());
	    Assert.assertTrue(bestand2.equals(bestand2Dummy));
	    
	    //Bestand NamedQuery FindAll
	    List<Bestand> bestandListeFindAll = new ArrayList<Bestand>();
	    bestandListeFindAll = bestandRepository.findAll();
	    Assert.assertEquals( bestandListeFindAll.size(), 2 );
	    Assert.assertTrue( bestandListeFindAll.contains( bestand1 ) );
	    Assert.assertTrue( bestandListeFindAll.contains( bestand2 ) );
	    
	    //Asocciation Artikel --[*]-> Bestand
	    Assert.assertTrue(bestand2Dummy.getArtikel().equals(artikel1Dummy));
	    
	    //Charge 1
	    Charge charge1dummy = manager.find(Charge.class, charge1.getCharge());
	    Assert.assertTrue(charge1.equals(charge1dummy));
	    //Asocciation Charge 1 --[*]-> Artikel
	    Assert.assertEquals(charge1.getArtikeln().size(), 1);
	    Assert.assertTrue(charge1.getArtikeln().contains(artikel1));
	    
	    
	    //Charge 2
	    Charge charge2dummy = manager.find(Charge.class, charge2.getCharge());
	    Assert.assertTrue(charge2.equals(charge2dummy));
	    //Asocciation Charge 2 --[*]-> Artikel
	    Assert.assertEquals(charge2.getArtikeln().size(), 2);
	    Assert.assertTrue(charge2.getArtikeln().contains(artikel1));
	    Assert.assertTrue(charge2.getArtikeln().contains(artikel2));
	    
	    //Charge NamedQuery FindAll
	    List<Charge> chargeListeFindAll = new ArrayList<Charge>();
	    chargeListeFindAll = chargeRepository.findAll();
	    Assert.assertEquals( chargeListeFindAll.size(), 2 );
	    Assert.assertTrue( chargeListeFindAll.contains( charge1 ) );
	    Assert.assertTrue( chargeListeFindAll.contains( charge2 ) );
	    
	    //Test Update
		transaction.begin();
		
		//Artikel 1
		artikel1.setBezeichnung("Bezeichnung");
		//Bestand
		bestand1.setMenge(BESTAND_MENGE_4);
		
		transaction.commit();
		
		//Artikel 1
		artikel1Dummy = manager.find(Artikelstamm.class, ARTIKELNUMMER_1);
	    Assert.assertTrue(artikel1.equals(artikel1Dummy));
	    //Bestand
	    bestand1Dummy = manager.find(Bestand.class, bestand1.getBestandsid());
	    Assert.assertEquals(bestand1Dummy.getMenge(), BESTAND_MENGE_4);
	    
	    //Test Delete
	    //==========================================================================
		transaction.begin();
		
		//Bestand
		bestand1 = manager.find(Bestand.class, bestand1.getBestandsid());
		manager.remove(bestand1);
		bestand2 = manager.find(Bestand.class, bestand2.getBestandsid());
		manager.remove(bestand2);
		
		//Charge 1
		charge1 = manager.find(Charge.class, charge1.getCharge());
		manager.remove(charge1);
		
		//Charge 2
		charge2 = manager.find(Charge.class, charge2.getCharge());
		manager.remove(charge2);
		
		//Artikel 1
		manager.remove(artikel1);
		
		//Artikel 2
		manager.remove(artikel2);
		
		//Artikel 3
		manager.remove(artikel3);
		
		transaction.commit();
		
		//find returns NULL if the ID is not found in the table
		Assert.assertNull(manager.find(Bestand.class, bestand1.getBestandsid()));
		Assert.assertNull(manager.find(Bestand.class, bestand2.getBestandsid()));
		
		//Bestand findAll -> leere Liste
		bestandListeFindAll.clear();
		bestandListeFindAll = bestandRepository.findAll();
	    Assert.assertEquals( bestandListeFindAll.size(), 0);
		
		//Charge findAll -> leere Liste
		chargeListeFindAll.clear();
		chargeListeFindAll = chargeRepository.findAll();
	    Assert.assertEquals( chargeListeFindAll.size(), 0);
		
		//Artikel findAll -> leere Liste
		artikelListeFindAll.clear();
	    artikelListeFindAll = artikelstammRepository.findAll();
	    Assert.assertEquals( artikelListeFindAll.size(), 0);
		
	}
	
	@After
	public void teardown()
	{
		manager.close();
		factory.close();
	}

}
