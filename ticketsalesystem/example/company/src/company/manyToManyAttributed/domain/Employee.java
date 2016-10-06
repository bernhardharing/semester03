/*
 * project    company
 * subproject manyToMany-attributed
*/

package company.manyToManyAttributed.domain;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.NamedQuery;

@Entity
    @NamedQuery(name="Employee.byAscendingWorkload",
                query = "SELECT junction.employee " +
                        "FROM   EmpProjJunction junction " +
                        "GROUP BY junction.employee.id " +
                        "ORDER BY SUM (junction.time_percent)" )

public class Employee {

    @SequenceGenerator( name           = "EmployeeIdGenerator",
                        sequenceName   = "Employee_Sequence",
                        allocationSize = 1)

    @Id @GeneratedValue(generator="EmployeeIdGenerator")
    private int id;
    private String firstName;
    private String lastName;

    @OneToMany (mappedBy = "employee")
    private List<EmpProjJunction> junctions = new ArrayList <> ();

    protected Employee() {}

    Employee (String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName  = lastName;
    }


    public int getId() {
        return id;
    }


    public void add (Project project, int time_percent)
    {
        EmpProjJunction junction = new EmpProjJunction (this, project, time_percent);

        if (junctions.contains(junction))                 // don't set again
    return;

        junctions.add ( junction );
        project.  add ( junction );
    }

    public int getTimePercent (Project project) throws IllegalStateException
    {
        for (EmpProjJunction junction : junctions)
        {
            if (project.equals (junction.getProject ()))
    return junction.getTimePercent();
        }
        return 0;
    }


    public  List<Project> getProjects() {
        List<Project> projects = new ArrayList<> ();

        for (EmpProjJunction junction : junctions)
        {
            Project project = junction.getProject ();
            projects.add ( project );
        }

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
        String string =  "Employee id: " + getId() + " name: " + getName();

        if (junctions.size() == 0)
            string += " workloads : none";
        else
        {

            string += " workload(s) : ";

            for (EmpProjJunction junction : junctions)
                string +=    junction.getProject().getName()
                           + " ( " + junction.getTimePercent() + " % ) ";
        }
        return string;
    }
}
