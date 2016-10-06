/*
 * project    company
 * subproject manyToManyAttributed
*/

package company.manyToManyAttributed.domain;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.CascadeType;

@Entity
public class Project {

    @SequenceGenerator( name           = "ProjectIdGenerator",
                        sequenceName   = "Project_Sequence",
                        allocationSize = 1)

    @Id @GeneratedValue(generator="ProjectIdGenerator")
    private int id;
    private String name;

    @OneToMany ( mappedBy="project", cascade = CascadeType.PERSIST)
    private List<EmpProjJunction> junctions = new ArrayList <> ();

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

    public int getTimePercent (Employee emp) throws IllegalStateException
    {
        for (EmpProjJunction junction : junctions)
        {
            if (emp.equals (junction.getEmployee ()))
    return junction.getTimePercent();
        }
        return 0;
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<> ();

        for (EmpProjJunction junction : junctions)
        {
            Employee emp = junction.getEmployee ();
            employees.add ( emp );
        }
        return employees;
    }


    void add (EmpProjJunction junction)
    {
        if (junctions.contains(junction))
    return;

        junctions.add (junction);
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
        String string = "Project id: " + getId() + ", name: " + getName();

        if (junctions.size() == 0)
            string += " workloads : none";
        else
        {

            string += " workload(s) : ";

            for (EmpProjJunction junction : junctions)
                string +=    junction.getEmployee().getName()
                           + " ( " + junction.getTimePercent() + " % ) ";

        }
        return string;

    }
}
