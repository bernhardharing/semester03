/*
 * project    company
 * subproject enumeration
*/


package company.enumeration.domain;

import spize.persistence.Persistence;

public class EmployeeRepository extends company.persistence.Repository<Employee>
{

    public EmployeeRepository()
    {
        super (Employee.class);
    }

    public Employee create (int id, String name, EmployeeType type)
    {
        Employee emp = new Employee(id, name, type);

        entityManager.persist(emp);

        return emp;
    }

    void reset ()
    {
		Persistence.resetTable    (schema, table);
	}

    static final String schema   = "enumeration";
    static final String table    = "employee";

}
