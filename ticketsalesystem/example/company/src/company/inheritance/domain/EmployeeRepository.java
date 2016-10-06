/*
 * project    company
 * subproject inheritance
*/

package company.inheritance.domain;

import spize.persistence.Persistence;

public class EmployeeRepository extends company.persistence.Repository<Employee>
                                implements company.persistence.IRepository<Employee>

{


    public EmployeeRepository()
    {
        super (Employee.class);
    }

    public FullTimeEmployee create (String firstName, String lastName
                                  , long salary)
    {

        FullTimeEmployee emp = new FullTimeEmployee (firstName, lastName, salary);

        entityManager.persist(emp);

        return emp;
    }

    public PartTimeEmployee create (String firstName, String lastName
                                  , double hourlyRate)
    {

        PartTimeEmployee emp = new PartTimeEmployee (firstName, lastName, hourlyRate);

        entityManager.persist(emp);

        return emp;
    }

    static void reset ()
    {
		Persistence.resetTable    (schema, table);
		Persistence.resetTable    (schema, tableFullTime);
		Persistence.resetTable    (schema, tablePartTime);
		Persistence.resetSequence (schema, sequence);
	}

    static final String schema           = "inheritance";
    static final String table            = "employee";
    static final String sequence         = "employee_sequence";
    static final String tableFullTime    = "full_time_employee";
    static final String tablePartTime    = "part_time_employee";


}
