/*
 * project    company
 * subproject queries
*/

package company.queries.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

@Entity @Table(name="Full_Time_Employee")

@NamedQueries({

    @NamedQuery(name="FullTimeEmployee.findSalaryByNameAndDepartment",
                query = "SELECT e.salary " +
                        "FROM   FullTimeEmployee e " +
                        "WHERE  e.department.name = :deptName " +
                        "  AND  e.firstName       = :firstName" +
                        "  AND  e.lastName        = :lastName" )

   ,@NamedQuery(name="FullTimeEmployee.paidMoreThanByDepartment",
                query = "SELECT e " +
                        "FROM   FullTimeEmployee e " +
                        "WHERE  e.department = :dept " +
                        "  AND  e.salary     > :minSalary" )

   ,@NamedQuery(name="FullTimeEmployee.paidHighestByDepartment",
                query="SELECT fte " +
                      "FROM FullTimeEmployee fte " +
                      "WHERE fte.department = :dept " +
                      "  AND fte.salary = (SELECT MAX(fte2.salary) " +
                      "                  FROM FullTimeEmployee fte2 " +
                      "                  WHERE fte2.department = :dept)")


})

public class FullTimeEmployee extends Employee
{


    private long salary;

    protected FullTimeEmployee() {}

    public  FullTimeEmployee (String firstName, String lastName, long salary)
    {
        super (firstName, lastName);

        this.salary = salary;
    }

    public long getSalary()     {   return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }


    @Override
    public String toString() {
        return super.toString() + " salary : " + getSalary();
    }


}
