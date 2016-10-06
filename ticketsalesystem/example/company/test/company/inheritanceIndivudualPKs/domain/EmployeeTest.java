/*
 * project    company
 * subproject inheritanceIndivudualPKs
*/

package company.inheritanceIndivudualPKs.domain;

import java.util.List;

import static org.junit.Assert.*;
import        org.junit.BeforeClass;
import        org.junit.AfterClass;
import        org.junit.Test;

import  spize.persistence.Transaction;

import  company.persistence.Persistence;

@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class EmployeeTest {

    static final boolean verbose = true;

    static final String    john_firstName   = "John";
    static final String    john_lastName    = "Doe";
    static final double    john_hourlyRate  = 7.24;

    static final String    homer_firstName   = "Homer";
    static final String    homer_lastName    = "Simpson";
    static final long      homer_salary      = 2999;

    static final long      FullTimeEmployee_Sequence_FirstVal = 1101;
    static final long      PartTimeEmployee_Sequence_FirstVal = 1501;

    static EmployeeRepository     empRepository;
    static PartTimeEmployee       john;
    static FullTimeEmployee       homer;

	@BeforeClass public static void setup()
	{
        empRepository   = new EmployeeRepository();
    }

	@AfterClass public static void teardown()
    {
        Persistence.close ();           // close the EM and EMF when done
    }

    @Test public void create ()
    {
        Transaction.begin();

        john =  empRepository.create  (john_firstName, john_lastName, john_hourlyRate);
        assertNotNull (john);

        homer = empRepository.create (homer_firstName, homer_lastName, homer_salary);
        assertNotNull (homer);


        Transaction.commit();

        if (verbose) System.out.println("Created and Persisted " + john);
        if (verbose) System.out.println("Created and Persisted " + homer);
    }

    @Test public void verify ()
    {

        assertTrue (john.getId() >= PartTimeEmployee_Sequence_FirstVal);

        assertTrue (homer.getId() >= FullTimeEmployee_Sequence_FirstVal);
        assertTrue (homer.getId() <  PartTimeEmployee_Sequence_FirstVal);

        List<Employee> emps = empRepository.findAll();

        if (verbose)  empRepository.printAll();

        assertTrue (emps.size () >= 2);

        System.out.println ("Passed   -------------------------------");

    }
}
