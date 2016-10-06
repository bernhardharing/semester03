/*
 * project    company
 * subproject PKCompound
*/

package company.PKCompound.domain;

import java.util.List;

import javax.persistence.TypedQuery;

import spize.persistence.Persistence;

public class EmployeeRepository extends company.persistence.Repository<Employee>
{

    public static final String findAll_JPQL  =
                    "SELECT e FROM Employee e order by e.pk.country, e.pk.id";

    public EmployeeRepository()
    {
        super (Employee.class);
    }

    public Employee create (EmployeePK pk, String firstName, String lastName)
    {
        Employee emp = new Employee (pk, firstName, lastName);

        entityManager.persist (emp);

        return emp;
    }

    public Employee find   (EmployeePK pk) {
        return entityManager.find ( Employee.class, pk);
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery (
                                            findAll_JPQL, Employee.class);
        return query.getResultList();
    }

    static void reset ()
    {
        Persistence.resetTable    (schema, table);
	}

    static final String schema   = "PKCompound";
    static final String table    = "employee";

}
