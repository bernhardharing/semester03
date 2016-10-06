/*
 * project    company
 * subproject enum_as_table
*/


package company.enum_as_table.domain;

import spize.persistence.Persistence;

public class EmployeeRepository extends company.persistence.Repository<Employee>
{

    public EmployeeRepository()
    {
        super (Employee.class);
    }

    public Employee create (int id, String name)
    {
        Employee emp = new Employee(id, name);

        entityManager.persist(emp);

        return emp;
    }

    void reset ()
    {
		Persistence.resetTable    (schema, table);
	}

    static final String schema   = "enum_as_table";
    static final String table    = "employee";

}
