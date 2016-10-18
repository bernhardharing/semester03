package at.fhj.swd.lagerverwaltung;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fhj.swd.lagerverwaltung.Bestellkopf;
import at.fhj.swd.lagerverwaltung.Kunde;

public class TestBestellung 
{	
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	private static BestellpositionRepository bestellpositionRepository;
	
	private final String persistenceUnitName = "Lagerverwaltung";
	private final String KUNDENNAME = "Kundenname";
	private final String ARTIKELNUMMER_1 = "1";
	private final String ARTIKELNUMMER_2 = "2";
	
	@Before
	public void setup() 
	{	
		factory = Persistence.createEntityManagerFactory(persistenceUnitName);
		manager = factory.createEntityManager();
		bestellpositionRepository = new BestellpositionRepository(manager);
	}
		
		
	@Test
	public void testPersist() 
	{		
		//Test Insert
		//==========================================================================
		transaction = manager.getTransaction();
		transaction.begin();
		
		//Kunde
		Kunde kunde1 = new Kunde("Max Mustermann");
		manager.persist(kunde1);
		
		//Kunde 
		Kunde kunde2 = new Kunde("Stefan");
		manager.persist(kunde2);
	    
	    //Bestellkopf 1
	    Bestellkopf bestellkopf1 = new Bestellkopf(kunde1);
	    manager.persist(bestellkopf1);
	    
	    //Bestellkopf 2 
	    Bestellkopf bestellkopf2 = new Bestellkopf(kunde1);
	    manager.persist(bestellkopf2);
	    
		//Artikel 1
		Artikelstamm artikel1 = new Artikelstamm(ARTIKELNUMMER_1, "Artikel 1");
		manager.persist(artikel1);
		
		//Artikel 2
		Artikelstamm artikel2 = new Artikelstamm(ARTIKELNUMMER_2, "Artikel 2");
		manager.persist(artikel2);
		
		//Bestellposition 1
		Bestellposition bestpos1 = new Bestellposition(bestellkopf1, 1, artikel1, 1);
		manager.persist(bestpos1);
		
		//Bestellposition 2
		Bestellposition bestpos2 = new Bestellposition(bestellkopf1, 2, artikel1, 1);
		manager.persist(bestpos2);
		
		//Bestellposition 3
		Bestellposition bestpos3 = new Bestellposition(bestellkopf1, 3, artikel2, 1);
		manager.persist(bestpos3);
	    
		transaction.commit();
		
		//Kunde
		Kunde kundeDummy = manager.find(Kunde.class, kunde1.getKundennummer());
	    Assert.assertTrue(kunde1.equals(kundeDummy));
	    //Asocciation Kunde --[*]-> Bestellkopf
	    Assert.assertEquals(kundeDummy.getBestellungen().size(), 2);
	    Assert.assertTrue(kundeDummy.getBestellungen().contains(bestellkopf1));
	    Assert.assertTrue(kundeDummy.getBestellungen().contains(bestellkopf2));
	    
	    //Bestellkopf 1
	    Bestellkopf bestellkopf1Dummy = manager.find(Bestellkopf.class, bestellkopf1.getBestellnummer());
	    Assert.assertTrue(bestellkopf1.equals(bestellkopf1Dummy));
	    //Asocciation Kunde --[*]-> Bestellkopf
	    Assert.assertTrue(bestellkopf1.getKunde().equals(kunde1));
	    //Asocciation Bestellkopf --[*]-> Bestellpos
	    
	    Assert.assertEquals(bestellkopf1.getPositionen().size(), 3);
	    Assert.assertTrue(bestellkopf1.getPositionen().contains(bestpos1));
	    Assert.assertTrue(bestellkopf1.getPositionen().contains(bestpos2));
	    Assert.assertTrue(bestellkopf1.getPositionen().contains(bestpos3));
	    
	    //Bestellkopf 2
	    Bestellkopf bestellkopf2Dummy = manager.find(Bestellkopf.class, bestellkopf2.getBestellnummer());
	    Assert.assertTrue(bestellkopf2.equals(bestellkopf2Dummy));
	    Assert.assertEquals(bestellkopf2.getPositionen().size(), 0);
	    //Asocciation Kunde --[*]-> Bestellkopf
	    Assert.assertTrue(bestellkopf2.getKunde().equals(kunde1));
  	    //Asocciation Bestellkopf --[*]-> Bestellposition
	    Assert.assertEquals(bestellkopf2.getPositionen().size(), 0);

	    //Bestellposition 1
	    Bestellposition bestpos1dummy = manager.find(Bestellposition.class, new BestellpositionId(bestpos1.getBestellkopf().getBestellnummer(), bestpos1.getPositionsnummer()));
	    Assert.assertTrue(bestpos1.equals(bestpos1dummy));
	    //Asocciation Bestellposition --[1]-> Bestellkopf
	    Assert.assertTrue(bestellkopf1.equals(bestpos1.getBestellkopf()));
	    //Asocciation Bestellposition --[1]-> Artikel
	    Assert.assertTrue(artikel1.equals(bestpos1.getArtikel()));
	    
	    //Bestellposition 2
	    Bestellposition bestpos2dummy = manager.find(Bestellposition.class, new BestellpositionId(bestpos2.getBestellkopf().getBestellnummer(), bestpos2.getPositionsnummer()));
	    Assert.assertTrue(bestpos2.equals(bestpos2dummy));
	    //Asocciation Bestellposition --[1]-> Bestellkopf
	    Assert.assertTrue(bestellkopf1.equals(bestpos2.getBestellkopf()));
	    //Asocciation Bestellposition --[1]-> Artikel
	    Assert.assertTrue(artikel1.equals(bestpos2.getArtikel()));
	    
	    //Bestellposition 3
	    Bestellposition bestpos3dummy = manager.find(Bestellposition.class, new BestellpositionId(bestpos3.getBestellkopf().getBestellnummer(), bestpos3.getPositionsnummer()));
	    Assert.assertTrue(bestpos3.equals(bestpos3dummy));
	    //Asocciation Bestellposition --[1]-> Bestellkopf
	    Assert.assertTrue(bestellkopf1.equals(bestpos3.getBestellkopf()));
	    //Asocciation Bestellposition --[1]-> Artikel
	    Assert.assertTrue(artikel2.equals(bestpos3.getArtikel()));
	    
	    //Bestellposition NamedQuery FindAll
	    List<Bestellposition> bestellpositionListeFindAll = new ArrayList<Bestellposition>();
	    bestellpositionListeFindAll = bestellpositionRepository.findAll();
	    Assert.assertEquals( bestellpositionListeFindAll.size(), 3 );
	    Assert.assertTrue( bestellpositionListeFindAll.contains( bestpos1 ) );
	    Assert.assertTrue( bestellpositionListeFindAll.contains( bestpos2 ) );
	    Assert.assertTrue( bestellpositionListeFindAll.contains( bestpos3 ) );
	    
	    //Test Update
		transaction.begin();
		
		//Kunde
		kunde1.setName(KUNDENNAME);
		
		//Bestellkopf
		bestellkopf1.setKunde(kunde2);
		
		//Bestellposition 1
		bestpos1.setArtikel(artikel2);
		
		transaction.commit();
		
		//Kunde
		kundeDummy = manager.find(Kunde.class, kunde1.getKundennummer());
	    Assert.assertTrue(kundeDummy.getName().equals(KUNDENNAME));
	    //Bestellkopf 1 darf dem Kunden nicht mehr zugewiesen sein
	    Assert.assertFalse( kunde1.getBestellungen().contains( bestellkopf1 ) );
	   
	    //Bestellkopf
	    bestellkopf1Dummy = manager.find(Bestellkopf.class, bestellkopf1.getBestellnummer());
	    Assert.assertTrue(bestellkopf1Dummy.getKunde().equals(kunde2));
	    
	    //Bestellposition 1
	    bestpos1dummy = manager.find(Bestellposition.class, new BestellpositionId(bestpos2.getBestellkopf().getBestellnummer(), bestpos2.getPositionsnummer()));
	    //Asocciation Bestellposition --[1]-> Artikel
	    Assert.assertTrue(artikel2.equals(bestpos1.getArtikel()));

	    
	    //Test Delete
	    //==========================================================================
		transaction.begin();
		
		//Bestellpositionen
		bestpos1 = manager.find(Bestellposition.class, new BestellpositionId(bestpos1.getBestellkopf().getBestellnummer(), bestpos1.getPositionsnummer()));
		manager.remove(bestpos1);
		bestpos2 = manager.find(Bestellposition.class, new BestellpositionId(bestpos2.getBestellkopf().getBestellnummer(), bestpos2.getPositionsnummer()));
		manager.remove(bestpos2);
		bestpos3 = manager.find(Bestellposition.class, new BestellpositionId(bestpos3.getBestellkopf().getBestellnummer(), bestpos3.getPositionsnummer()));
		manager.remove(bestpos3);
		
		//Bestellkopf
		bestellkopf1 = manager.find(Bestellkopf.class, bestellkopf1.getBestellnummer());
		manager.remove(bestellkopf1);
		bestellkopf2 = manager.find(Bestellkopf.class, bestellkopf2.getBestellnummer());
		manager.remove(bestellkopf2);
		
		//Kunde
		kunde1 = manager.find(Kunde.class, kunde1.getKundennummer());
		manager.remove(kunde1);
		
		//Artikel
		artikel1 = manager.find(Artikelstamm.class, ARTIKELNUMMER_1);
		manager.remove(artikel1);
		artikel2 = manager.find(Artikelstamm.class, ARTIKELNUMMER_2);
		manager.remove(artikel2);
		
		transaction.commit();
		
		//Returns Null if the PK is not fouund
		Assert.assertNull(manager.find(Bestellkopf.class, bestellkopf1.getBestellnummer()));
		Assert.assertNull(manager.find(Bestellkopf.class, bestellkopf2.getBestellnummer()));
		Assert.assertNull(manager.find(Kunde.class, kunde1.getKundennummer()));
		Assert.assertNull(manager.find(Artikelstamm.class, ARTIKELNUMMER_1));
		Assert.assertNull(manager.find(Artikelstamm.class, ARTIKELNUMMER_2));
		
		//Bestellposition findAll -> leere Liste
		bestellpositionListeFindAll.clear();
		bestellpositionListeFindAll = bestellpositionRepository.findAll();
	    Assert.assertEquals( bestellpositionListeFindAll.size(), 0);
	}
	
	@After
	public void teardown()
	{
		manager.close();
		factory.close();
	}
}
