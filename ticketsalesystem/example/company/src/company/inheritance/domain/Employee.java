/*
 * project    company
 * subproject inheritance
*/

package company.inheritance.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Employee {
    @SequenceGenerator( name="EmployeeIdGenerator",
                        sequenceName="Employee_Sequence",
                        allocationSize = 1)

    @Id @GeneratedValue(generator="EmployeeIdGenerator")
    protected int id;

    protected String firstName;
    protected String lastName;

    protected Employee() {}

    public Employee (String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName  = lastName;
    }

    public int getId()          {   return id;
    }
    public String getFirstName(){   return firstName;
    }
    public String getLastName() {   return lastName;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " id: " + getId() + " name: " + getFirstName()
               + ' ' + getLastName();
    }
}
