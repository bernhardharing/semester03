/*
 * project    company
 * subproject manyToMany
*/

package company.manyToMany.domain;

import java.util.List;

import static org.junit.Assert.*;
import        org.junit.BeforeClass;
import        org.junit.AfterClass;
import        org.junit.Test;

import spize.persistence.Transaction;

import  company.persistence.Persistence;

@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class EmployeeProjectTest
{
    static final boolean verbose = true;

    static final String    emp1_firstName   = "Bart";
    static final String    emp1_lastName    = "Simpson";
    static final String    emp2_firstName   = "Lisa";
    static final String    emp2_lastName    = "Simpson";
    static final String    emp3_firstName   = "Spider";
    static final String    emp3_lastName    = "Man";

    static final String    proj1_name  = "Clean the kitchen";
    static final String    proj2_name = "Impress people";

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
        EmployeeRepository.reset ();
        ProjectRepository. reset ();
        Transaction.commit();
    }

	@AfterClass public static void teardown()
	{
        Persistence.close ();       // close the EM and EMF when done
    }

    @Test public void create ()
    {
        Transaction.begin();

        bart        = empRepository.create(emp1_firstName, emp1_lastName);
        lisa        = empRepository.create(emp2_firstName, emp2_lastName);
        spiderMan   = empRepository.create(emp3_firstName, emp3_lastName);

        assertNotNull (bart);
        assertNotNull (lisa);
        assertNotNull (spiderMan);


        kitchen_cleaning = projRepository.create (proj1_name);
        impress_people = projRepository.create (proj2_name);

        assertNotNull (kitchen_cleaning);
        assertNotNull (impress_people);

        Transaction.commit();
    }

    //  let employees work for above projects   --------------------
    @Test public void join ()
    {
        Transaction.begin();

        lisa.add        (kitchen_cleaning);
        bart.add        (impress_people);
        spiderMan.  add (kitchen_cleaning);
        spiderMan.  add (impress_people);

        Transaction.commit();
    }

    @Test public void verify ()
    {
        if (verbose)  empRepository.printAll();
        if (verbose) projRepository.printAll();

        // Employees --------------------------------------------------
        List<Employee> emps = empRepository.findAll();

        assertEquals ( 3, emps.size ());
        assertTrue   ( emps.contains  (bart));
        assertTrue   ( emps.contains  (spiderMan));


        // Projects   -------------------------------------------------
        List<Project> projs = projRepository.findAll();

        assertEquals ( 2, projs.size ());
        assertTrue   ( projs.contains (kitchen_cleaning));
        assertTrue   ( projs.contains (impress_people));

        // Project -> Employees ---------------------------------------

        assertEquals ( 2,  kitchen_cleaning.getEmployees().size());
        assertEquals ( 2, impress_people.getEmployees().size());

        assertTrue   ( kitchen_cleaning.getEmployees().contains (lisa));
        assertFalse  ( kitchen_cleaning.getEmployees().contains (bart));

        // Employees -> Project   -------------------------------------

        assertEquals ( 1, bart.getProjects().size());
        assertFalse  ( bart.getProjects().contains (kitchen_cleaning));
        assertTrue   ( bart.getProjects().contains (impress_people));

        assertEquals ( 2, spiderMan  .getProjects().size());
        assertTrue   ( spiderMan.getProjects().contains (kitchen_cleaning));
        assertTrue   ( spiderMan.getProjects().contains (impress_people));
    }
}
