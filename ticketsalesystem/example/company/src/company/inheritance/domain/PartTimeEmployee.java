/*
 * project    company
 * subproject inheritance
*/

package company.inheritance.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name="Part_Time_Employee")
public class PartTimeEmployee extends Employee
{


    private double hourlyRate;

    protected PartTimeEmployee() {}

    public  PartTimeEmployee (String firstName, String lastName, double hourlyRate)
    {
        super (firstName, lastName);

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
