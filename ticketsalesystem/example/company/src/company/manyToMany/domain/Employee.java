/*
 * project    company
 * subproject manyToMany
*/

package company.manyToMany.domain;

import java.util.Collection;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;

@Entity
public class Employee {

    @SequenceGenerator( name="EmployeeIdGenerator",
                        sequenceName="Employee_Sequence",
                        allocationSize = 1)

    @Id @GeneratedValue(generator="EmployeeIdGenerator")
    private int id;
    private String firstName;
    private String lastName;

    @ManyToMany
    @JoinTable (         joinColumns = @JoinColumn (name="EMPLOYEE_ID")
                 ,inverseJoinColumns = @JoinColumn (name="PROJECT_ID") )
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

    public void add (Project project)
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

    public boolean equals (Employee e)
    {
        return e.id == id;
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
        return "Employee id: " + getId() + " name: " + getName() +
               " with " + getProjects().size() + " project(s)";

    }
}
