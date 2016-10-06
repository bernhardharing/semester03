/*
 * project    company
 * subproject PKCompound
*/

package company.PKCompound.domain;

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


    static final EmployeePK    emp1_pk     = new  EmployeePK ("AT", 007);
    static final String        emp1_firstName   = "John";
    static final String        emp1_lastName    = "Doe";

    static final EmployeePK    emp2_pk     = new  EmployeePK ("DE", 007);
    static final String        emp2_firstName   = "Jack";
    static final String        emp2_lastName    = "Ripper";


    static EmployeeRepository     empRepository;

	@BeforeClass public static void setup()
    {
        empRepository   = new EmployeeRepository();

        Transaction.begin();
        EmployeeRepository.  reset ();
        Transaction.commit();
    }

	@AfterClass public static void teardown()
	{
        Persistence.close ();       // close the EM and EMF when done
    }

    @Test public void first_create ()
    {

        Transaction.begin();

        Employee john = empRepository.create(emp1_pk, emp1_firstName, emp1_lastName);
        Employee jack = empRepository.create(emp2_pk, emp2_firstName, emp2_lastName);

        assertNotNull (john);
        assertNotNull (jack);

        Transaction.commit();

        if (verbose) System.out.println("Created and Persisted " + john);
        if (verbose) System.out.println("Created and Persisted " + jack);
    }

    @Test public void first_verify ()
    {
        Employee john = empRepository.find (emp1_pk);
        Employee jack = empRepository.find (emp2_pk);

        assertNotNull (john);
        assertNotNull (jack);

        List<Employee> emps = empRepository.findAll();
        assertEquals ( 2, emps.size ());

        assertTrue ( emps.contains ( john  ));
        assertTrue ( emps.contains ( jack  ));

        if (verbose) for (Employee em : emps)
                         System.out.println("Found " + em);
    }

    @Test public void second_create ()
    {
        System.out.println("\ncreating an employee a 2nd time  m u s t  fail:\n");

        Transaction.begin();

        empRepository.create(emp1_pk, emp1_firstName, emp1_lastName);

        try
        {
            Transaction.commit();

            fail ();
        }
        catch (Exception exc)
        {
        }

    }
}
