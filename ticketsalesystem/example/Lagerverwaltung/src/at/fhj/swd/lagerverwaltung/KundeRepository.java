package at.fhj.swd.lagerverwaltung;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class KundeRepository {
	
	private EntityManager entityManager;
	
	public KundeRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	

	/*
	 * Dynamic query to find all orders to a certain customer
	 */
	public List<Bestellkopf> findBestellungenToOfKunde(int kundennummer) {
		TypedQuery<Bestellkopf> query = entityManager.createQuery("SELECT b FROM Bestellkopf b JOIN b.kunde k WHERE k.kundennummer = :kundennummer", Bestellkopf.class);
		return query.setParameter("kundennummer", kundennummer).getResultList();
	}
	
	/*
	 * Query to get the number of order to a certain customer
	 */
	public long findNumberOfBestellungenToKunde(int kundennummer) {
		Query query = entityManager.createQuery("SELECT COUNT(b) FROM Bestellkopf b JOIN b.kunde k WHERE k.kundennummer = :kundennummer");
	
		return (long) query.setParameter("kundennummer", kundennummer).getSingleResult();
	}
}
