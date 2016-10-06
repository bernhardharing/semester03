/*
 * project    company
 * subproject PKCompound
*/

package company.PKCompound.domain;

import javax.persistence.Embeddable;

@Embeddable
public class EmployeePK {

    private String country;
    private int    id;

    protected EmployeePK () { }

    public    EmployeePK (String country, int id)
    {
        this.country = country;
        this.id      = id;
    }

    public String getCountry () { return country; }
    public int    getId      () { return id     ; }


    @Override public boolean equals (Object o)
    {
        return o instanceof EmployeePK
            && ((EmployeePK) o).id ==  id
            && ((EmployeePK) o).country.equals (country);
    }

    @Override public int hashCode ()
    {
        return id + country.hashCode() ;
    }

    @Override
    public String toString() {
        return country + id;
    }


}
