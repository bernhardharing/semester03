/*
 * project    company
 * subproject queries
*/

package company.queries.domain;

import java.util.Collection;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;

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


    @OneToOne
    private ParkingSpace parking_space;

    @ManyToOne (optional = true)
    private Department department;

    @ManyToMany
    private Collection<Project> projects = new ArrayList <> ();

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

        parking_space.setEmployee (this);
    }


    public Department getDepartment() {
        return department;
    }

    public void setDepartment (Department department)
    {
        if (department.equals (this.department))        // don't set again
    return;

        if (this.department != null && ! department.equals (this.department))
        {
            changeDepartment (department);
        }
        else
        {
            this.department = department;

            department.add (this);
        }
    }

    private void changeDepartment(Department department)
    {

        this.department.remove (this);

        this.department = null;

        setDepartment (department);
    }


    public void addProject (Project project)
    {
        if (projects.contains(project))                 // don't set again
    return;

        projects.add (project);

        project.add (this);
    }

    public Collection<Project> getProjects() {
        return projects;
    }

    @Override public boolean equals (Object o)
    {
        return o instanceof Employee && ((Employee) o).id == id;
    }

    @Override public int hashCode ()
    {
        return id;
    }

    public String getFirstName(){   return firstName;
    }
    public String getLastName() {   return lastName;
    }
    public String getName() {   return firstName + ' ' + lastName;
    }

    @Override public String toString()
    {
        String string = "Employee id: " + getId() + " name: " + getName() +
                        " with " + getProjects().size() + " project(s)";

        if (getDepartment() != null)
            string += " department: " + getDepartment().getId ()
                                + "(" + getDepartment().getName () + ")";

        if (getParkingSpace() != null)
            string += " ;car parked in " + getParkingSpace();


        return string;

    }
}
