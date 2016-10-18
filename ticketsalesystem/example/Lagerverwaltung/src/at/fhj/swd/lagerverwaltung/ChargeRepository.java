package at.fhj.swd.lagerverwaltung;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ChargeRepository 
{
	/*
	 * Constructor
	 */
	public ChargeRepository(final EntityManager entityManager)
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
	public List<Charge> findAll()
	{
		TypedQuery<Charge> query = entityManager.createNamedQuery( "Charge.findAll", Charge.class);
		
		return query.getResultList();
	}	
}
