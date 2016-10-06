/*
 * project    company
 * subproject oneToOneSamePK
*/

package company.oneToOneSamePK.domain;

import java.util.List;

import static org.junit.Assert.*;
import        org.junit.BeforeClass;
import        org.junit.AfterClass;
import        org.junit.Test;

import  spize.persistence.Transaction;

import  company.persistence.Persistence;

@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class EmployeeNonDisclosureTest {

    static final boolean verbose = true;

    static final String    emp1_firstName   = "Homer";
    static final String    emp1_lastName    = "Simpson";
    static final int       emp1_id          =  1111;

    static final String    emp2_firstName   = "Jack";
    static final String    emp2_lastName    = "Ripper";

    static final Grade     nondisc1_grade   = Grade.POOR;
    static final String    nondisc1_info    = "sleeps most of the time";

    static EmployeeRepository     empRepository;
    static NonDisclosureRepository nondiscRepository;


	@BeforeClass public static void setup()
    {
        empRepository   = new EmployeeRepository();
        nondiscRepository = new NonDisclosureRepository();

        Transaction.begin();
        nondiscRepository.reset ();
        empRepository.  reset ();
        Transaction.commit();
    }

	@AfterClass public static void teardown()
	{
        Persistence.close ();       //
    }


    @Test public void create ()
    {
        Transaction.begin();

        Employee homer   =
            empRepository.    create (emp1_id, emp1_firstName, emp1_lastName);
        NonDisclosure nondisc =
            nondiscRepository.create (homer, nondisc1_grade, nondisc1_info);

        assertNotNull (homer);
        assertNotNull (nondisc);


        Transaction.commit();

        if (verbose) System.out.println("Persisted " + homer);
        if (verbose) System.out.println("Persisted " + nondisc);

    }

    @Test public void verify ()
    {
        Employee homer =        empRepository    .find (emp1_id);
        NonDisclosure nondisc = nondiscRepository.find (emp1_id);

        // Same PKs   ---------------------------------------------

        assertEquals ( homer.getId(), nondisc.getId());

        // Employee   -------------------------------------------------

        List<Employee> emps = empRepository.findAll();
        if (verbose) for (Employee emp : emps)
                         System.out.println("Found " + emp);

        assertEquals ( 1,  emps.size ());


        assertNotNull (homer);

        assertEquals (homer, emps.get ( 0 ));

        assertEquals (nondisc, homer.getNonDisclosure() );

        // NonDisclosure   ----------------------------------------

        List<NonDisclosure> nondiscs = nondiscRepository.findAll();
        if (verbose) for (NonDisclosure nd : nondiscs)
                         System.out.println("Found " + nd);

        assertEquals ( 1, nondiscs.size ());

        assertEquals ( nondisc, nondiscs.get (0));

        assertEquals ( homer, nondisc.getEmployee());


    }
}
