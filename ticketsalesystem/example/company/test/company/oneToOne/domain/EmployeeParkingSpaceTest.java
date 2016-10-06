/*
 * project    company
 * subproject oneToOne
*/

package company.oneToOne.domain;

import java.util.List;

import static org.junit.Assert.*;
import        org.junit.BeforeClass;
import        org.junit.AfterClass;
import        org.junit.Test;

import  spize.persistence.Transaction;

import  company.persistence.Persistence;

@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class EmployeeParkingSpaceTest {

    static final boolean verbose = true;

    static final String    emp1_firstName   = "John";
    static final String    emp1_lastName    = "Doe";
    static final int       emp1_id          =     1;

    static final String    emp2_firstName   = "Jack";
    static final String    emp2_lastName    = "Ripper";

    static final int       space_lot      = 4711;
    static final String    space_location = "near-elevator";

    static EmployeeRepository     empRepository;
    static ParkingSpaceRepository spaceRepository;

    static Employee     john;
    static ParkingSpace johns_space;

	@BeforeClass public static void setup()
    {
        empRepository   = new EmployeeRepository();
        spaceRepository = new ParkingSpaceRepository();

        Transaction.begin();
        empRepository.  reset ();
        spaceRepository.reset ();
        Transaction.commit();
    }

	@AfterClass public static void teardown()
	{
        Persistence.close ();       // close the EM and EMF when done
    }


    @Test public void create ()
    {
        Transaction.begin();

        john = empRepository.create(emp1_firstName, emp1_lastName);

        johns_space = spaceRepository.create (space_lot, space_location);

        assertNotNull (john);
        assertNotNull (johns_space);


        Transaction.commit();

        if (verbose) System.out.println("Persisted " + john);
        if (verbose) System.out.println("Persisted " + johns_space);

    }

    @Test public void join ()
    {
        Transaction.begin();

        john.setParkingSpace (johns_space);

        Transaction.commit();
    }

    @Test public void verify ()
    {

        // Employee   -------------------------------------------------

        List<Employee> emps = empRepository.findAll();
        assertEquals ( 1,  emps.size ());

        john = empRepository.find (emp1_id);
        assertNotNull (john);

        assertEquals (john, emps.get ( 0 ));

        assertEquals (johns_space, john.getParkingSpace() );

        if (verbose) for (Employee em : emps)
                         System.out.println("Found " + em);

        // ParkingSpace   ----------------------------------------

        List<ParkingSpace> spaces = spaceRepository.findAll();
        assertEquals ( 1, spaces.size ());

        assertEquals ( johns_space, spaces.get (0));

        if (verbose) for (ParkingSpace ps : spaces)
                         System.out.println("Found " + ps);


        assertEquals ( john, johns_space.getEmployee());


        // assigning a 2nd emp. to the same space must fail   ---------
        System.out.println("\nassigning a 2nd emp. to the same space  m u s t  fail :\n");

        Transaction.begin();
        Employee jack = empRepository.create(emp2_firstName, emp2_lastName);

        jack.setParkingSpace (johns_space);
        try
        {
            Transaction.commit();

            fail();
        }
        catch (Exception exc)
        {
        }

    }
}
