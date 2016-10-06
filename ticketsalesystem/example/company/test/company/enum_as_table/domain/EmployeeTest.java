/*
 * project    company
 * subproject enumeration
*/

package company.enum_as_table.domain;


import static org.junit.Assert.*;
import        org.junit.BeforeClass;
import        org.junit.AfterClass;
import        org.junit.Test;

import  spize.persistence.Transaction;

import  company.persistence.Persistence;

@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class EmployeeTest {

    static final boolean verbose = true;

    static final int           id     = 158;
    static final String        name   = "John Doe";
    static final int           fullTimeTypeID = 1;

    static EmployeeRepository repository;

	@BeforeClass public static void setup()
	{
        Persistence.connect ();

        repository = new EmployeeRepository();

    }

	@AfterClass public static void teardown()
    {
        Transaction.begin();
        repository.reset ();
        Transaction.commit();

        Persistence.close ();
    }

    @Test public void create ()
    {

        //  create and persist John   ---------------------------------
        Transaction.begin();
        Employee john = repository.create (id, name);

        john.setType (fullTimeTypeID);

        Transaction.commit();

        assertNotNull (john);
        if (verbose) System.out.println("Persisted " + john);
    }

    @Test public void find ()
    {
        Employee john = repository.find (id);
        assertNotNull (john);
        if (verbose) System.out.println("Found " + john);

        assertEquals (fullTimeTypeID, john.getTypeId());

    }
}
