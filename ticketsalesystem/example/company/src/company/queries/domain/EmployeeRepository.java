/*
 * project    company
 * subproject queries
*/

package company.queries.domain;

import java.util.List;

import javax.persistence.TypedQuery;

public class EmployeeRepository extends company.persistence.Repository <Employee>
{

    public EmployeeRepository ()
    {
        super (Employee.class);
    }

    public EmployeeRepository (String user, String password)
    {
        super (user, password, Employee.class);
    }

    public FullTimeEmployee create (String firstName, String lastName, long salary)
    {
        FullTimeEmployee emp = new FullTimeEmployee (firstName, lastName, salary);

        entityManager.persist (emp);

        return emp;
    }

    public PartTimeEmployee create (String firstName, String lastName, double hourlyRate)
    {
        PartTimeEmployee emp = new PartTimeEmployee (firstName, lastName, hourlyRate);

        entityManager.persist (emp);

        return emp;
    }

    public List <Employee> withoutProjects ()
    {

        final String queryText = "SELECT e FROM Employee e " +
                                 "WHERE  e.projects IS EMPTY";

        TypedQuery<Employee> query = entityManager.createQuery (
                                             queryText
                                            ,Employee.class );

        return query.getResultList();
    }

    public List <Employee> withoutParkingSpace ()
    {

        /* I) explicit JOIN  */
        final String queryText = "SELECT e FROM Employee e " +
                                 "LEFT JOIN e.parking_space p " +
                                 "WHERE  p IS NULL";

        /* II) implicit join */
//      final String queryText = "SELECT e FROM Employee e " +
//                               "WHERE  e.parking_space IS NULL" )

        /* nB : both "IS NULL" and "= null" work :-) */


        TypedQuery<Employee> query = entityManager.createQuery (
                                            queryText
                                            ,Employee.class );

        return query.getResultList();
    }

    public Employee find (String firstName, String lastName)
    {
        final String queryText = "SELECT e FROM Employee e " +
                                 "WHERE  e.firstName = :firstName" +
                                 "  AND  e.lastName  = :lastName";

        TypedQuery<Employee> query = entityManager.createQuery (
                                             queryText
                                            ,Employee.class );

        query.setParameter ("firstName", firstName);
        query.setParameter ( "lastName",  lastName);

        return query.getSingleResult();
    }

    public long getSalary (String firstName, String lastName, String deptName)
    {
        TypedQuery<Long> query = entityManager.createNamedQuery (
                                            "FullTimeEmployee.findSalaryByNameAndDepartment"
                                            ,Long.class );

        query.setParameter ("firstName", firstName);
        query.setParameter ( "lastName",  lastName);
        query.setParameter ( "deptName",  deptName);

        return query.getSingleResult();
    }

}
