/*
 * project    company
 * subproject manyToMany
*/

package company.manyToMany.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;

@Entity
public class Project {

    @SequenceGenerator( name="ProjectIdGenerator",
                        sequenceName="Project_Sequence",
                        allocationSize = 1)

    @Id @GeneratedValue(generator="ProjectIdGenerator")
    protected int id;
    protected String name;

    @ManyToMany ( mappedBy = "projects" )
    private Collection<Employee> employees = new ArrayList<>();

    protected Project () {
    }

    Project (String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public Collection<Employee> getEmployees() {
        return employees;
    }

    void add (Employee employee)
    {
        if (employees.contains(employee))
    return;

        employees.add(employee);
    }

    @Override public boolean equals (Object o)
    {
        return o instanceof Project && ((Project) o).id == id;
    }

    @Override public int hashCode ()
    {
        return id;
    }

    @Override public String toString()
    {
        return "Project id: " + getId() + ", name: " + getName() +
               " with " + getEmployees().size() + " employee(s)";
    }
}
