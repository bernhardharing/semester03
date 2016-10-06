/*
 * project    company
 * subproject inheritanceIndivudualPKs
*/

package company.inheritanceIndivudualPKs.domain;


import javax.persistence.Query;


public class EmployeeRepository extends company.persistence.Repository<Employee>
{

    private static final String schema         = "inheritanceIndivudualPKs";

    public EmployeeRepository()
    {
        super (Employee.class);
    }

    public FullTimeEmployee create (String firstName, String lastName
                                  , long salary)
    {
        int id = createPK ("FullTimeEmployee");

        FullTimeEmployee emp = new FullTimeEmployee (id, firstName, lastName, salary);

        entityManager.persist(emp);

        return emp;
    }

    public PartTimeEmployee create (String firstName, String lastName
                                  , double hourlyRate)
    {
        int id = createPK ("PartTimeEmployee");

        PartTimeEmployee emp = new PartTimeEmployee (id, firstName, lastName, hourlyRate);

        entityManager.persist(emp);

        return emp;
    }

    private int createPK (String className) {
        Query query = entityManager.createNativeQuery (
            "SELECT NEXTVAL ('" + schema + "." + className + "_Sequence')" );

        Long pk = (Long) query.getSingleResult();

        return pk.intValue();
    }

}
