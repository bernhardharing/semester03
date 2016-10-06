/*
 * project    company
 * subproject enum_as_table
*/


package company.enum_as_table.domain;

import spize.persistence.Persistence;

public class EmployeeTypeRepository extends company.persistence.Repository<EmployeeType>
{

    public EmployeeTypeRepository()
    {
        super (EmployeeType.class);
    }

}
