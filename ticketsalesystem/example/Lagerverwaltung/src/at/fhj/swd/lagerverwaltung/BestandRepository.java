package at.fhj.swd.lagerverwaltung;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class BestandRepository 
{
	/*
	 * Constructor
	 */
	public BestandRepository(final EntityManager entityManager)
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
	public List<Bestand> findAll()
	{
		TypedQuery<Bestand> query = entityManager.createNamedQuery( "Bestand.findAll", Bestand.class);
		
		return query.getResultList();
	}	
}
