/*
 * project    company
 * subproject queries
*/

package company.queries.aggregate;

public class DepartmentStatistics {

    private String departmentName;
    private Long   fullTimeEmpoyeeCount;
    private Long   fullTimeEmpoyeeMaxSalary;
    private Double fullTimeEmpoyeeAvgSalary;

    public DepartmentStatistics ( String departmentName
                                 ,Long   fullTimeEmpoyeeCount
                                 ,Long   fullTimeEmpoyeeMaxSalary
                                 ,Double fullTimeEmpoyeeAvgSalary)
    {
        this.departmentName           = departmentName;
        this.fullTimeEmpoyeeCount     = fullTimeEmpoyeeCount;
        this.fullTimeEmpoyeeMaxSalary = fullTimeEmpoyeeMaxSalary;
        this.fullTimeEmpoyeeAvgSalary = fullTimeEmpoyeeAvgSalary;
    }


    // TODO : declare getters

    @Override public String toString()
    {
        return departmentName + " : full time emps = " + fullTimeEmpoyeeCount
              + " ; AVG salary = " + fullTimeEmpoyeeAvgSalary
              + " ; MAX salary = " + fullTimeEmpoyeeMaxSalary;
    }

}
