/*
 * project    company
 * subproject oneToOneSamePK
*/

package company.oneToOneSamePK.domain;

import java.util.List;

import javax.persistence.TypedQuery;

import spize.persistence.Persistence;

public class NonDisclosureRepository extends    company.persistence.Repository <NonDisclosure>
                                    implements company.persistence.IRepository<NonDisclosure>
{


    public NonDisclosureRepository ()
    {
        super (NonDisclosure.class);
    }

    public NonDisclosure create (Employee employee, Grade grade, String info) {

        NonDisclosure nondisc = new NonDisclosure(employee, grade, info);

        entityManager.persist(nondisc);

        employee.setNonDisclosure (nondisc);

        return nondisc;
    }

    @Override
    public List<NonDisclosure> findAll() {
        TypedQuery<NonDisclosure> query = entityManager.createQuery (
                "SELECT nondisc FROM "
                     + " NonDisclosure nondisc"
                     + " ORDER BY nondisc.employee.id"
                ,NonDisclosure.class);

        return query.getResultList();
    }

    void reset ()
    {
		Persistence.resetTable    (schema, table);
	}

    static final String schema   = EmployeeRepository.schema;
    static final String table    = "NON_DISCLOSURE";
}
