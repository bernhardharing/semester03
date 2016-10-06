/*
 * project    company
 * subproject nheritanceIndivudualPKs
*/

package company.inheritanceIndivudualPKs.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name="Full_Time_Employee")


public class FullTimeEmployee extends Employee
{

    private long salary;

    protected FullTimeEmployee() {}

    FullTimeEmployee (int id, String firstName, String lastName, long salary)
    {
        super (id, firstName, lastName);

        this.salary = salary;
    }



    public long getSalary()             {   return salary;    }

    public void setSalary(long salary)  {   this.salary = salary;    }


    @Override
    public String toString() {
        return super.toString() + " salary : " + getSalary();
    }


}
