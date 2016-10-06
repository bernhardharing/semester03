/*
 * project    company
 * subproject oneToOneSamePK
*/

package company.oneToOneSamePK.domain;

import spize.persistence.Persistence;

public class EmployeeRepository extends    company.persistence.Repository <Employee>
                                implements company.persistence.IRepository<Employee>
{

    public EmployeeRepository()
    {
        super (Employee.class);
    }

    public Employee create (int id, String firstName, String lastName)
    {
        Employee emp = new Employee (id, firstName, lastName);

        entityManager.persist (emp);

        return emp;
    }

    void reset ()
    {
		Persistence.resetTable    (schema, table);
	}

    static final String schema   = "oneToOneSamePK";
    static final String table    = "employee";

}
