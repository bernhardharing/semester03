/*
 * project    company
 * subproject sequence
*/

package company.sequence.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;


@Entity
public class Employee {

    @SequenceGenerator (name="EmployeeIdGenerator",
                        sequenceName="Employee_Sequence",
                        allocationSize = 1)

    @Id @GeneratedValue (generator="EmployeeIdGenerator")
    private int id;
    private String name;
    private long salary;

    protected Employee () {}

    Employee (String name, long salary) {
        this.name = name;
        setSalary (salary);
    }

    @Override
    public boolean equals (Object o)
    {
        return o instanceof Employee && ((Employee) o).id ==  id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() + " salary: " + getSalary();
    }
}
