/*
 * project    company
 * subproject PKCompound
*/


package company.PKCompound.domain;

import javax.persistence.Entity;
import javax.persistence.EmbeddedId;


@Entity
public class Employee {

    @EmbeddedId
    private EmployeePK pk;
    private String firstName;
    private String lastName;


    protected Employee() {}

    Employee (EmployeePK pk, String firstName, String lastName)
    {
        this.pk        = pk;
        this.firstName = firstName;
        this.lastName  = lastName;
    }

    public EmployeePK getPK() {
        return pk;
    }


    public String getFirstName(){   return firstName;
    }
    public String getLastName() {   return lastName;
    }
    public String getName() {   return firstName + ' ' + lastName;
    }


    @Override
    public String toString() {

        return "Employee PK : " + getPK() + " name: " + getName();

    }

}
