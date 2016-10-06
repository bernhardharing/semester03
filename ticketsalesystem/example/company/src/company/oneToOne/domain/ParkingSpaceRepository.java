/*
 * project    company
 * subproject oneToOne
*/

package company.oneToOne.domain;

import spize.persistence.Persistence;

public class ParkingSpaceRepository extends    company.persistence.Repository <ParkingSpace>
                                    implements company.persistence.IRepository<ParkingSpace>
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

    void reset ()
    {
		Persistence.resetTable    (schema, table);
		Persistence.resetSequence (schema, sequence);
	}

    static final String schema   = EmployeeRepository.schema;
    static final String table    = "PARKING_SPACE";
    static final String sequence = "parking_space_id_seq";
}
