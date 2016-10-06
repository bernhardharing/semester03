/*
 * project    company
 * subproject enumeration
*/

package company.enumeration.domain;


import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Enumerated;
import javax.persistence.EnumType;

@Entity
public class Employee {
    @Id
    private int id;
    private String name;

    @Enumerated (EnumType.STRING)
    private EmployeeType type;

    protected Employee () { }

    Employee (int id, String name, EmployeeType type) {
        this.id = id;
        this.name = name;
        setType (type);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }


    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() +
               " type: " + getType();
    }
}
