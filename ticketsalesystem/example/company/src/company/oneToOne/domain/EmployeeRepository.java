/*
 * project    company
 * subproject oneToOne
*/

package company.oneToOne.domain;

import spize.persistence.Persistence;

public class EmployeeRepository extends    company.persistence.Repository <Employee>
                                implements company.persistence.IRepository<Employee>
{

    public EmployeeRepository()
    {
        super (Employee.class);
    }

    public Employee create (String firstName, String lastName)
    {
        Employee emp = new Employee (firstName, lastName);

        entityManager.persist (emp);

        return emp;
    }

    void reset ()
    {
		Persistence.resetTable    (schema, table);
		Persistence.resetSequence (schema, sequence);
	}

    static final String schema   = "oneToOne";
    static final String table    = "employee";
    static final String sequence = "employee_id_seq";

}
