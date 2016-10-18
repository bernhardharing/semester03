package at.fhj.swd.lagerverwaltung;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.jpa.config.NamedQuery;

public class ArtikelstammRepository 
{
	/*
	 * Constructor
	 */
	public ArtikelstammRepository(final EntityManager entityManager)
	{
		setEntityMangager( entityManager );
	}
	
	/*
	 * Property EntityManager
	 */
	private EntityManager entityManager;
	public void setEntityMangager(final EntityManager entityManager)
	{
		if(entityManager == null)
			throw new IllegalArgumentException("EntityManager is invalid!");
		
		this.entityManager = entityManager;
	}
	
	//NamedQuery FindAll
	public List<Artikelstamm> findAll()
	{
		TypedQuery<Artikelstamm> query = entityManager.createNamedQuery( "Artikelstamm.findAll", Artikelstamm.class);
		
		return query.getResultList();
	}
	
	//NamedQuery FindByBezeichnung
	public Artikelstamm findByBezeichnung(final String bezeichnung)
	{
		TypedQuery<Artikelstamm> query = entityManager.createNamedQuery( "Artikelstamm.findByBezeichnung", Artikelstamm.class);
		
		query.setParameter("bezeichnung", bezeichnung);
		
		return query.getSingleResult();
	}
	//NamedQuery FindBestaende
	public List<ArtikelBestand> findBestaende(final String artikelnummer)
	{
		List<ArtikelBestand> list = null;
		TypedQuery<ArtikelBestand> query = entityManager.createNamedQuery( "Artikelstamm.findBestaende", ArtikelBestand.class);
		query.setParameter("artikelnummer", artikelnummer);
		list = query.getResultList();
		
		return list;	
	}
	
}
