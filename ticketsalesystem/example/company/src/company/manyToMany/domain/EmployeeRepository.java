/*
 * project    company
 * subproject mantyoMany
*/

package company.manyToMany.domain;

import spize.persistence.Persistence;

public class EmployeeRepository extends    company.persistence.Repository <Employee>
                                implements company.persistence.IRepository<Employee>
{

    public  EmployeeRepository ()
    {
        super ( Employee.class);
    }


    public Employee create (String firstName, String lastName)
    {
        Employee emp = new Employee (firstName, lastName);

        entityManager.persist (emp);

        return emp;
    }

    // friend company.manyToManyAttributed.EmployeeRepository !
    public static void reset ()
    {
		Persistence.resetTable    (schema, junctionTable);
		Persistence.resetTable    (schema, table);
		Persistence.resetSequence (schema, sequence);
	}

    static final String schema           = "manyToMany";
    static final String table            = "employee";
    static final String sequence         = "employee_sequence";
    static final String junctionTable    = "employee_project";

}
