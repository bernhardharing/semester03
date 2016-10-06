/*
 * project    company
 * subproject oneToOne
*/

package company.oneToOne.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;

    @OneToOne
    private ParkingSpace parking_space;


    protected Employee() {}

    Employee (String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName  = lastName;
    }

    public int getId() {
        return id;
    }


    public ParkingSpace getParkingSpace() {
        return parking_space;
    }

    public void setParkingSpace (ParkingSpace parking_space)
    {
        this.parking_space = parking_space;

        parking_space.setEmployee (this);   // NOT done 'automatically' !!!
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

        if (getParkingSpace() != null)
            string = string + "at " + getParkingSpace();

        return string;

    }

}
