package at.fhj.swd;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Bogdan on 24.10.2016.
 */
public class CustomerRepository {
    public CustomerRepository(final EntityManager entityManager)
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
    public List<Customer> findAll()
    {
        TypedQuery<Customer> query = entityManager.createNamedQuery( "Customer.findAll", Customer.class);

        return query.getResultList();
    }
}
