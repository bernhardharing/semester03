/*
 * project    company
 * subproject inheritance
*/

package company.inheritance.domain;

import java.util.List;

import static org.junit.Assert.*;
import        org.junit.BeforeClass;
import        org.junit.AfterClass;
import        org.junit.Test;

import spize.persistence.Transaction;
import spize.util.Printer;

import company.persistence.Persistence;



@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class EmployeeTest {

    static final boolean verbose = true;

    static final String    john_firstName   = "John";
    static final String    john_lastName    = "Doe";
    static final double    john_hourlyRate  = 7.24;

    static final String    homer_firstName   = "Homer";
    static final String    homer_lastName    = "Simpson";
    static final long      homer_salary      = 2999;

    static PartTimeEmployee john;
    static FullTimeEmployee homer;

    static EmployeeRepository   empRepository;

	@BeforeClass public static void setup()
    {
        empRepository = new EmployeeRepository();

        Transaction.begin();
        EmployeeRepository.reset ();
        Transaction.commit();

    }

	@AfterClass public static void teardown()
	{
        Persistence.close ();
    }

    @Test public void create ()
    {

        Transaction.begin ();

        john = empRepository.create (john_firstName,  john_lastName, john_hourlyRate);
        homer= empRepository.create (homer_firstName, homer_lastName, homer_salary);

        assertNotNull (homer);
        assertNotNull (john);

        if (verbose) System.out.println("Persisted " + john);
        if (verbose) System.out.println("Persisted " + homer);

        Transaction.commit();
    }

    @Test public void find ()
    {

        List<Employee> emps = empRepository.findAll();

        Printer.print (emps, "all employees");

        assertEquals (2, emps.size());

        Employee emp = empRepository.find (john.getId());

        assertTrue ( emp instanceof PartTimeEmployee );

        emp = empRepository.find (homer.getId());

        assertTrue ( emp instanceof FullTimeEmployee );


    }
}
