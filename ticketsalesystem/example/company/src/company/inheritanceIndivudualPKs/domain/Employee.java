/*
 * project    company
 * subproject inheritanceIndivudualPKs
*/

package company.inheritanceIndivudualPKs.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;



@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Employee {

    @Id
    protected int id;

    protected String firstName;
    protected String lastName;

    protected Employee() {}

    public Employee (int id, String firstName, String lastName)
    {
        this.id        = id;
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
