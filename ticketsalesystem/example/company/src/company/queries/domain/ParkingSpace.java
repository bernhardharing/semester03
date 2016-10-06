/*
 * project    company
 * subproject qeries
*/

package company.queries.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;


@Entity
@Table(name="PARKING_SPACE")
public class ParkingSpace {

    @SequenceGenerator( name="ParkingSpaceIdGenerator",
                        sequenceName="ParkingSpace_Sequence",
                        allocationSize = 1)

    @Id @GeneratedValue(generator="ParkingSpaceIdGenerator")
    private int id;

    private int lot;
    private String location;

    @OneToOne(mappedBy="parking_space")
    private Employee employee;


    protected ParkingSpace() {}

    ParkingSpace (int lot, String location)
    {
        this.lot = lot;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public int getLot() {
        return lot;
    }

    public String getLocation() {
        return location;
    }

    void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
            return employee;
    }

    @Override public boolean equals (Object o)
    {
        return o instanceof ParkingSpace && ((ParkingSpace) o).id == id;
    }

    @Override public int hashCode ()
    {
        return id;
    }


    @Override
    public String toString() {
        String string =  "ParkingSpace id: " + getId() + " lot: " + getLot() +
               ", location: " + getLocation();

        if (getEmployee() != null)
            string += " for " + getEmployee().getName();

        return string;
    }

}
