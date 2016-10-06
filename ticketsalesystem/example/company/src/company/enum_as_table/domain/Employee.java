/*
 * project    company
 * subproject enum_as_table
*/

package company.enum_as_table.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
    @Id
    private int id;
    private String name;

    @ManyToOne
    private EmployeeType employeeType;

    protected Employee () { }

    Employee (int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return employeeType.getType();
    }

    int getTypeId() {
        return employeeType.getId();
    }

    public void setType (int EmployeeTypeID) {
        employeeType = new EmployeeTypeRepository().find (EmployeeTypeID);
    }


    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() +
               " type: " + getType();
    }
}
