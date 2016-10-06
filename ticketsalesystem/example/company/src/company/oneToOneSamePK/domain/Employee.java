/*
 * project    company
 * subproject oneToOneSamePK
*/

package company.oneToOneSamePK.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Employee {

    @Id
    private int id;

    @OneToOne ( mappedBy="employee" )
    private NonDisclosure nonDisclosure;

    private String firstName;
    private String lastName;


    protected Employee() {}

    Employee (int id, String firstName, String lastName)
    {
        this.id        = id;
        this.firstName = firstName;
        this.lastName  = lastName;
    }

    public int getId() {
        return id;
    }


    public NonDisclosure getNonDisclosure() {
        return nonDisclosure;
    }

    void setNonDisclosure (NonDisclosure nonDisclosure) {
        this.nonDisclosure = nonDisclosure;
    }

    public String getFirstName(){   return firstName;
    }
    public String getLastName() {   return lastName;
    }
    public String getName() {   return firstName + ' ' + lastName;
    }

    @Override
    public String toString() {

        String string = "Employee id: " + getId() + " name: " + getName();

        if (getNonDisclosure() != null)
            string = string + " nondisclosable info : " + getNonDisclosure();

        return string;

    }

}
