/*
 * project    company
 * subproject manyToMany-attributed
*/

package company.manyToManyAttributed.domain;


import javax.persistence.Entity;
import javax.persistence.EmbeddedId;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity @Table(name="EMPLOYEE_PROJECT")

class EmpProjJunction
{
    @EmbeddedId
    private EmpProjJunctionPK pk;

    @ManyToOne ( optional = false )
    @JoinColumn ( insertable = false, updatable = false )
    private Employee employee;

    @ManyToOne ( optional = false )
    @JoinColumn ( insertable = false, updatable = false )
    private Project project;

    private int time_percent;

    protected EmpProjJunction () {}

    EmpProjJunction (Employee employee, Project project, int timePercent)
    {
        this.pk            = new EmpProjJunctionPK (employee.getId()
                                                   ,project. getId());

        this.employee      = employee;
        this.project       = project;
        this.time_percent  = timePercent;
    }

    Employee getEmployee    () { return employee; }
    Project  getProject     () { return project; }
    int      getTimePercent () { return time_percent; }


}
