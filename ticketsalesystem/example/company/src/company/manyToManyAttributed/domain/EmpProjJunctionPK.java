/*
 * project    company
 * subproject manyToMany-attributed
*/

package company.manyToManyAttributed.domain;

import javax.persistence.Embeddable;

@Embeddable
class EmpProjJunctionPK
{

    private Integer employee_Id;       // MUST be same name as table col.
    private Integer project_Id;        // MUST be same name as table col.

    protected EmpProjJunctionPK () { }

    EmpProjJunctionPK (int employeeId, int projectId)
    {
        this.employee_Id = employeeId;
        this.project_Id  = projectId;
    }


    @Override public boolean equals (Object o)
    {
        return o instanceof EmpProjJunctionPK
            && ((EmpProjJunctionPK) o).employee_Id == employee_Id
            && ((EmpProjJunctionPK) o).project_Id  == project_Id;
    }

    @Override public int hashCode ()
    {
        return employee_Id + project_Id;
    }

    @Override
    public String toString() {
        return employee_Id + ";" + project_Id;
    }


}
