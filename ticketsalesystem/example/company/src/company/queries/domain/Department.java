/*
 * project    company
 * subproject queries
*/

package company.queries.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;

import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

@Entity

@NamedQueries({

/**/
    // style a) path expression
    @NamedQuery ( name="Department.employees",
                  query = "SELECT e FROM Employee e " +
                          "WHERE  e.department.name = :name"
                )
/**/
/*
    // style b) JOIN
    @NamedQuery ( name="Department.employees",
                  query = "SELECT e FROM Employee e " +
                          "JOIN   e.department d "    +
                          "WHERE  d.name = :name"
                )
*/
/*
    // style c) JOIN the other way round - also ok
    @NamedQuery ( name="Department.employees",
                  query = "SELECT e FROM Department d " +
                          "JOIN   d.employees e "    +
                          "WHERE  d.name = :name"
                )
*/

   ,@NamedQuery ( name="Department.findByName",
                  query = "SELECT d FROM Department d " +
                          "WHERE  d.name = :name"
                )
/*
    // JPA 2.0
   ,@NamedQuery ( name="Department.statistics",
                    query = "SELECT  NEW company.queries.aggregate.DepartmentStatistics("
                      + "d.name, COUNT(fte), "
                      + "MAX(fte.salary), AVG(fte.salary) ) "
                      + "FROM Department d, FullTimeEmployee fte "
                      + "WHERE fte MEMBER OF d.employees "
                      + "GROUP BY d "
                )
*/
    // JPA 2.1
   ,@NamedQuery ( name="Department.statistics",
                    query = "SELECT  NEW company.queries.aggregate.DepartmentStatistics("
                      + "d.name, COUNT(fte), "
                      + "MAX(fte.salary), AVG(fte.salary) ) "
                      + "FROM Department d JOIN TREAT (d.employees AS FullTimeEmployee) fte "
                      + "GROUP BY d "
                )

   ,@NamedQuery(name="Department.involvedInProjects",
                query = "SELECT DISTINCT p  " +
                        "FROM Department d JOIN d.employees e JOIN e.projects p " +
                        "WHERE d.name = :departmentName " )

})

public class Department {

    @SequenceGenerator( name="DepartmentIdGenerator",
                        sequenceName="Department_Sequence",
                        allocationSize = 1)

    @Id @GeneratedValue(generator="DepartmentIdGenerator")

    private int id;
    private String name;

    @OneToMany(mappedBy="department")
    private Collection<Employee> employees  = new ArrayList<>();

    protected Department ()     { }

    Department (String name)
    {
        this.name = name;
    }

    @Override public boolean equals (Object o)
    {
        return o instanceof Department && ((Department) o).id ==  id;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String deptName) {
        this.name = deptName;
    }


    void add (Employee employee)
    {
        if (employees.contains(employee))
    return;

        employees.add(employee);
    }

    void remove (Employee employee)
    {
        employees.remove (employee);
    }

    public List<Employee> getEmployees()
    {
        return (List<Employee>) employees;
    }


    @Override public String toString()
    {
        String string = "Department id: " + getId() + ", name: " + getName();

        if (employees == null)
             string += ", employees == null !!!";
        else if (employees.size() == 0)
             string += " employees: none";
        else
        {
             string += " employees: ";
             for (Employee emp : employees) string += " " + emp.getName();
        }

        return string;

    }
}
