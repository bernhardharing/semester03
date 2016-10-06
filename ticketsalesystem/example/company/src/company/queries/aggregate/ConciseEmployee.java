/*
 * project    company
 * subproject queries
*/

package company.queries.aggregate;

import  company.queries.domain.Employee;

public class ConciseEmployee {

    private String employeeLastName;
    private String departmentName;

    public ConciseEmployee (Employee employee)
    {
        this.employeeLastName = employee.getLastName();

        if (employee.getDepartment() != null)
            this.departmentName   = employee.getDepartment().getName();
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public String getDepartmentName() {
        return departmentName;
    }


    @Override public String toString()
    {
        return employeeLastName + " ( " + departmentName + " )" ;
    }

}

