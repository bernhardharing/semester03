package at.fhj.swd;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Bogdan on 25.10.2016.
 */
public class StadiumRepository {
    public StadiumRepository(final EntityManager entityManager)
    {
        setEntityManager( entityManager );
    }

    private EntityManager entityManager;
    public void setEntityManager(final EntityManager entityManager)
    {
        if(entityManager == null)
            throw new IllegalArgumentException("EntityManager is invalid!");

        this.entityManager = entityManager;
    }

    //NamedQuery FindAll
    public List<Stadium> findAll()
    {
        TypedQuery<Stadium> query = entityManager.createNamedQuery( "Stadium.findAll", Stadium.class);

        return query.getResultList();
    }
}
