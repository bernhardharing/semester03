package at.fhj.swd;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bernhard on 18.10.2016.
 */

public class CategoryRepository {

    public CategoryRepository(final EntityManager entityManager)
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
    public List<Category> findAll()
    {
        TypedQuery<Category> query = entityManager.createNamedQuery( "Category.findAll", Category.class);

        return query.getResultList();
    }
}
