/*
 * project    company
 * subproject manyToMany-attributed
*/

package company.manyToManyAttributed.domain;

import java.util.List;

import javax.persistence.TypedQuery;

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

    public Employee laziest ()
    {
        TypedQuery<Employee> query = entityManager.createNamedQuery (
                                            "Employee.byAscendingWorkload"
                                            ,Employee.class );

        List<Employee> byAscendingWorkload = query.getResultList();

        if (byAscendingWorkload.isEmpty())
             return null;
        else return byAscendingWorkload.get(0);
    }


    static void reset ()
    {
        company.manyToMany.domain.EmployeeRepository.reset ();
	}


}
