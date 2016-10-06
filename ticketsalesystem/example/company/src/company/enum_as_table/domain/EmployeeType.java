/*
 * project    company
 * subproject enum_as_table
*/

package company.enum_as_table.domain;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class EmployeeType {
    @Id
    private int id;
    private String type;

    protected EmployeeType () { }

    EmployeeType (int id) { this.id = id; }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }


}
