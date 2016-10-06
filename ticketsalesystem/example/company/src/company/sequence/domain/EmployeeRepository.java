/*
 * project    company
 * subproject sequence
*/

package company.sequence.domain;

import spize.persistence.Persistence;

public class EmployeeRepository extends company.persistence.Repository<Employee>
{

    public EmployeeRepository()
    {
        super (Employee.class);
    }


    public Employee create (String name, int salary) {
        Employee emp = new Employee (name, salary);

        entityManager.persist(emp);

        return emp;
    }

    /* make table empty and set sequence to initial value */
    static void reset ()
    {
		Persistence.resetTable    (schema, table);
		Persistence.resetSequence (schema, sequence);
	}

    static final String schema   = "sequence";
    static final String table    = "employee";
    static final String sequence = "Employee_Sequence";

}
