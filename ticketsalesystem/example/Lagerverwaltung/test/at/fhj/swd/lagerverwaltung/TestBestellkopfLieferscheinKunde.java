package at.fhj.swd.lagerverwaltung;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

public class TestBestellkopfLieferscheinKunde {
	
	private  EntityManagerFactory factory;
	private  EntityManager manager;
	private  EntityTransaction transaction;
	
	private KundeRepository kundeRepository;
	
	private final String PERSISTENCEUNITNAME = "Lagerverwaltung";
	private final String KUNDENNAME = "Testkunde";
	private final String KUNDENNAME_NEU = "TestkundeNeu";
	private final String KUNDENNAME_UPDATE = "TestkundeUPDATE";
	
	@Before
	public void setup() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCEUNITNAME);
		manager = factory.createEntityManager();
		
	}
	
	
	@Test
	public void testPersist() {
		
		
		
		transaction = manager.getTransaction();
		transaction.begin();
		
		//KundeRepository
		kundeRepository = new KundeRepository(manager);
		
		//Kunde
		Kunde kunde = new Kunde(KUNDENNAME);
		manager.persist(kunde);
		
		//Bestellkopf
		Bestellkopf bestellkopf = new Bestellkopf(kunde);
		manager.persist(bestellkopf);
		
		//Lieferschein
		Lieferschein lieferschein = new Lieferschein(bestellkopf);
		manager.persist(lieferschein);
		
		transaction.commit();
		
		
		//Kunde
		Kunde kundeDummy = manager.find(Kunde.class, kunde.getKundennummer());
		assertEquals(kunde, kundeDummy);
		//Association Kunde --[*]> Bestellkopf
		assertEquals(kundeDummy.getBestellungen().size(), 1);
		assertTrue(kundeDummy.getBestellungen().contains(bestellkopf));
		
		
		//Bestellkopf
		Bestellkopf bestellkopfDummy = manager.find(Bestellkopf.class, bestellkopf.getBestellnummer());
		assertEquals(bestellkopf, bestellkopfDummy);
		//Association Bestellkopf --[1]> Kunde
		assertEquals(bestellkopfDummy.getKunde(), kunde);
		//Association Bestellkopf --[1]> Lieferschein		
		assertEquals(bestellkopfDummy.getLieferschein(), lieferschein);
		
		
		//Lieferschein
		Lieferschein lieferscheinDummy = manager.find(Lieferschein.class, lieferschein.getLieferscheinnummer());
		assertEquals(lieferschein, lieferscheinDummy);
		//Association Lieferschein --[1]> Bestellkopf
		assertEquals(lieferscheinDummy.getBestellkopf(), bestellkopf);
		
		
		//Update		
		transaction.begin();
		
		//Kunde
		kunde.setName(KUNDENNAME_UPDATE);
		
		//Bestellkopf
		Kunde kundeNeu = new Kunde(KUNDENNAME_NEU);
		manager.persist(kundeNeu);
		bestellkopf.setKunde(kundeNeu);
		
		//Lieferschein
		Bestellkopf bestellkopfNeu = new Bestellkopf(kundeNeu);
		manager.persist(bestellkopfNeu);
		lieferschein.setBestellkopf(bestellkopfNeu);
		
		transaction.commit();
		
		//Kunde
		kundeDummy = manager.find(Kunde.class, kunde.getKundennummer());
		assertEquals(kundeDummy.getName(), KUNDENNAME_UPDATE);
		
		//Bestellkopf
		bestellkopfDummy = manager.find(Bestellkopf.class, bestellkopf.getBestellnummer());
		assertEquals(bestellkopfDummy.getKunde().getKundennummer(), kundeNeu.getKundennummer());
		
		//Lieferschein
		lieferscheinDummy = manager.find(Lieferschein.class, lieferschein.getLieferscheinnummer());
		assertEquals(lieferscheinDummy.getBestellkopf(), bestellkopfNeu);
		
		
		
		//Queries
		
		//Bestellungen zu Kundennummer
		assertTrue(kundeRepository.findBestellungenToOfKunde(kundeNeu.getKundennummer()).contains(bestellkopfNeu));
		assertTrue(kundeRepository.findBestellungenToOfKunde(kundeNeu.getKundennummer()).contains(bestellkopf));
		
		assertEquals(0, kundeRepository.findBestellungenToOfKunde(kunde.getKundennummer()).size());
		
		//Anzahl Bestellungen zu Kundennummer
		assertEquals(2, kundeRepository.findNumberOfBestellungenToKunde(kundeNeu.getKundennummer()));
		
		
		//Delete
		transaction.begin();
		
		manager.remove(kunde);
		manager.remove(lieferschein);
		manager.remove(bestellkopf);
		
		manager.remove(kundeNeu);
		manager.remove(bestellkopfNeu);
		
		assertNull(manager.find(Kunde.class, kunde.getKundennummer()));
		assertNull(manager.find(Lieferschein.class, lieferschein.getLieferscheinnummer()));
		assertNull(manager.find(Bestellkopf.class, bestellkopf.getBestellnummer()));
		assertNull(manager.find(Kunde.class, kundeNeu.getKundennummer()));
		assertNull(manager.find(Bestellkopf.class, bestellkopfNeu.getBestellnummer()));
		
		transaction.commit();
		
	}
	
	public void teardown() {
		manager.close();
		factory.close();
	}
}
