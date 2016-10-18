package at.fhj.swd.lagerverwaltung;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class BestellpositionRepository 
{
	/*
	 * Constructor
	 */
	public BestellpositionRepository(final EntityManager entityManager)
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
	public List<Bestellposition> findAll()
	{
		TypedQuery<Bestellposition> query = entityManager.createNamedQuery( "Bestellposition.findAll", Bestellposition.class);
		
		return query.getResultList();
	}	
}
