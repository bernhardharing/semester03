/*
 * project    company
 * subproject manyToMany-attributed
*/

package company.manyToManyAttributed.domain;

import java.util.List;

import static org.junit.Assert.*;
import        org.junit.BeforeClass;
import        org.junit.AfterClass;
import        org.junit.Test;

import spize.persistence.Transaction;
import company.persistence.Persistence;

@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class EmployeeProjectTest
{
    static final boolean verbose = true;

    static final String    bart_firstName   = "Homer";
    static final String    bart_lastName    = "Simpson";
    static final String    lisa_firstName   = "Lisa";
    static final String    lisa_lastName    = "Simpson";
    static final String    spiderMan_firstName   = "Spider";
    static final String    spiderMan_lastName    = "Man";

    static final String    kitchen_cleaning_name  = "Clean the kitchen";
    static final String    impress_people_name = "Impress people";

    static final int       lisa_kitchen_cleaning_workload = 50;
    static final int       bart_impress_people_workload = 33;
    static final int       spiderMan_kitchen_cleaning_workload = 1;
    static final int       spiderMan_impress_people_workload   = 99;

    static EmployeeRepository   empRepository;
    static ProjectRepository   projRepository;

    static  Employee bart;
    static  Employee lisa;
    static  Employee spiderMan;

    static Project  kitchen_cleaning;
    static Project  impress_people;

	@BeforeClass public static void setup()
    {
        empRepository = new EmployeeRepository();
        projRepository = new ProjectRepository();

        Transaction.begin();
        EmployeeRepository. reset ();
        ProjectRepository.reset ();
        Transaction.commit();
    }

	@AfterClass public static void teardown()
	{
        Persistence.close ();
    }

    @Test public void create ()
    {
        Transaction.begin();

        bart = empRepository.create(bart_firstName, bart_lastName);
        lisa  = empRepository.create(lisa_firstName, lisa_lastName);
        spiderMan   = empRepository.create(spiderMan_firstName, spiderMan_lastName);

        assertNotNull (bart);
        assertNotNull (lisa);
        assertNotNull (spiderMan);


        kitchen_cleaning = projRepository.create (kitchen_cleaning_name);
        impress_people = projRepository.create (impress_people_name);

        assertNotNull (kitchen_cleaning);
        assertNotNull (impress_people);

        Transaction.commit();
    }


    //  let employees work for above projects   --------------------
    @Test public void join ()
    {
        Transaction.begin();

        lisa.add (kitchen_cleaning, lisa_kitchen_cleaning_workload);
        bart. add (impress_people, bart_impress_people_workload);
        spiderMan.  add (kitchen_cleaning, spiderMan_kitchen_cleaning_workload);
        spiderMan.  add (impress_people, spiderMan_impress_people_workload);

        Transaction.commit();
    }

    @Test public void query ()
    {
        Employee laziest = empRepository.laziest();

        if (verbose)  System.out.println ("laziest employee : " + laziest);

        assertEquals (bart_firstName, laziest.getFirstName());
    }

    @Test public void verify ()
    {


        // Employees   ------------------------------------------------
        List<Employee> emps = empRepository.findAll();

        assertEquals ( 3,  emps.size ());
        assertTrue   ( emps.contains  (bart));
        assertTrue   ( emps.contains  (spiderMan));

        // Projects ---------------------------------------------------

        List<Project> projs = projRepository.findAll();

        assertEquals ( 2,  projs.size ());
        assertTrue   ( projs.contains (kitchen_cleaning));
        assertTrue   ( projs.contains (impress_people));

        // Project -> Employees ---------------------------------------

        if (verbose) System.out.println (kitchen_cleaning);

        assertEquals ( 2, kitchen_cleaning.getEmployees().size());

        assertEquals ( lisa_kitchen_cleaning_workload,        kitchen_cleaning.getTimePercent(lisa));
        assertEquals ( spiderMan_kitchen_cleaning_workload   ,kitchen_cleaning.getTimePercent(spiderMan));

        if (verbose) System.out.println (impress_people);

        assertEquals ( 2, impress_people.getEmployees().size());

        // Employees -> Project   -------------------------------------

        if (verbose) System.out.println (bart);

        assertEquals ( 1, bart.getProjects().size());
        assertTrue   ( bart.getProjects().contains (impress_people));

        assertEquals ( bart_impress_people_workload
                      ,bart.getTimePercent(impress_people));

        assertFalse  ( bart.getProjects().contains (kitchen_cleaning));

        if (verbose) System.out.println (spiderMan);

        assertEquals ( 2, spiderMan  .getProjects().size());
        assertTrue   ( spiderMan.getProjects().contains (kitchen_cleaning));
        assertTrue   ( spiderMan.getProjects().contains (impress_people));

        assertEquals ( spiderMan_kitchen_cleaning_workload, spiderMan.getTimePercent (kitchen_cleaning));
        assertEquals ( spiderMan_impress_people_workload, spiderMan.getTimePercent (impress_people));

    }
}
