/*
 * project    company
 * subproject queries
*/

package company.queries.domain;

import java.util.List;

import javax.persistence.TypedQuery;

public class ParkingSpaceRepository extends company.persistence.Repository <ParkingSpace>
{

    public ParkingSpaceRepository ()
    {
        super (ParkingSpace.class);
    }

    public ParkingSpace create (int lot, String location) {

        ParkingSpace space = new ParkingSpace(lot, location);

        entityManager.persist(space);

        return space;
    }

    public List <ParkingSpace> free ()
    {
        final String queryText =
                        "SELECT p FROM ParkingSpace p " +
                        "WHERE NOT p.id IN " +
                        "  ( SELECT e.parking_space.id FROM  Employee e " +
                        "    WHERE e.parking_space IS NOT NULL)";

        TypedQuery<ParkingSpace> query = entityManager.createQuery (
                                             queryText
                                            ,ParkingSpace.class );

        return query.getResultList();
    }

}
