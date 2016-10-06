/*
 * project    company
 * subproject simplest
*/

package company.simplest.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    private int id;
    private String firstName;
    private String lastName;
    private long salary;

    protected Employee() {}

    public Employee (int id, String firstName, String lastName, long salary)
    {
        this.id        = id;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.salary    = salary;
    }

    public int getId()          {   return id;
    }

    public String getFirstName(){   return firstName;
    }
    public String getLastName() {   return lastName;
    }

    public long getSalary()     {   return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public void raiseSalary(long salary_increase) {
        this.salary += salary_increase;
    }

    public String toString() {
        return "Employee id: " + getId() + " name: " + getFirstName()
               + ' ' + getLastName() + " salary: " + getSalary();
    }
}
