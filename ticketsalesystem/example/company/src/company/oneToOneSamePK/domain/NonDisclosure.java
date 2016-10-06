/*
 * project    company
 * subproject oneToOneSamePK
*/

package company.oneToOneSamePK.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import javax.persistence.Enumerated;
import javax.persistence.EnumType;

@Entity
@Table(name="NON_DISCLOSURE")
public class NonDisclosure {

    @Id
    @OneToOne
    @JoinColumn(name="EMPLOYEE_ID")
    private Employee employee;

    @Enumerated (EnumType.STRING)
    private Grade  grade;
    private String info;

    protected NonDisclosure() {}

    NonDisclosure (Employee employee, Grade grade, String info)
    {
        this.employee = employee;
        this.grade    = grade;
        this.info     = info;
    }

    public int getId() {
        return employee.getId();
    }

    public Grade getGrade() {
        return grade;
    }

    public String getInfo() {
        return info;
    }

    public Employee getEmployee() {
            return employee;
    }

    @Override
    public String toString() {
        String string =  "NonDisclosure id: " + getId() + " grade: " + getGrade() +
               ", info: " + getInfo();

        if (getEmployee() != null)
            string += " for " + getEmployee().getName();

        return string;
    }

}
