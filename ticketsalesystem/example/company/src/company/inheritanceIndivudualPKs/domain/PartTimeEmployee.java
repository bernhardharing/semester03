/*
 * project    company
 * subproject inheritanceIndivudualPKs
*/

package company.inheritanceIndivudualPKs.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name="Part_Time_Employee")
public class PartTimeEmployee extends Employee
{


    private double hourlyRate;

    protected PartTimeEmployee() {}

    PartTimeEmployee (int id, String firstName, String lastName, double hourlyRate)
    {
        super (id, firstName, lastName);

        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate()     {   return hourlyRate;
    }

    public void setHourlyRate (double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }


    @Override
    public String toString() {
        return super.toString() + " hourly Rate : " + getHourlyRate();
    }


}
