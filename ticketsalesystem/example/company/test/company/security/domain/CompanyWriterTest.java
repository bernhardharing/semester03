/*
 * project    company
 * subproject security
*/

package company.security.domain;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
import        org.junit.BeforeClass;
import        org.junit.AfterClass;
import        org.junit.Test;

import  spize.persistence.Transaction;

import  company.queries.domain.FullTimeEmployee;

import  company.queries.domain.EmployeeRepository;
import  company.queries.domain.ProjectRepository;
import  company.queries.domain.DepartmentRepository;
import  company.queries.domain.ParkingSpaceRepository;


import  company.persistence.Persistence;

@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class CompanyWriterTest extends CompanyTest
{

    final static String user     = "smithers";
    final static String password = "burns";

    /* resetting what we did in the last run is a little bit tricky :
     * The WRITER role can create entities (which we did), but is not
     * allowed to delete them. That's why we need to connect as the
     * default user (which has to be the owner or super-user) to
     * delete the entities.
    */
    static void reset ()
    {
        EntityManager em = Persistence.connect ();

        reset (em);

        teardown();
    }

	@BeforeClass public static void setup()
	{
        reset ();

        Persistence.connect (user, password);

        empRepository = new EmployeeRepository();
        projRepository = new ProjectRepository();
        deptRepository = new DepartmentRepository();
        pSpaceRepository = new ParkingSpaceRepository();
    }

	@AfterClass public static void teardown()
	{
        Persistence.close ();
    }


    @Test public void createEntity ()
    {
        Transaction.begin();

        empRepository.create ( emp_new_firstName, emp_new_lastName, emp_new_salary);
        Transaction.commit();
    }

    @Test public void modifyEntity ()
    {
        Transaction.begin();

        FullTimeEmployee fte_new = (FullTimeEmployee)
                                        empRepository.find (emp_new_firstName
                                                           ,emp_new_lastName);
        assertTrue (fte_new.getId() != 0);

        fte_new.setSalary ( fte_new.getSalary() + 1 );

        Transaction.commit();
    }

    // WRITER has no right no to remove an entity ...
    @Test public void removeEntity ()
    {

        try
        {
            reset (Persistence.getEntityManager());

            fail ();
        }
        catch (Exception exc)
        {
            assertTrue (permissionDenied (exc));
            Transaction.rollback();
        }
    }

    // WRITER has no right no to create a VIEW (or TABLE) !
    @Test public void createView ()
    {
        Transaction.begin();

        try
        {
            Persistence.getEntityManager().createNativeQuery(createViewStm).executeUpdate();

            Transaction.commit ();

            fail ();
        }
        catch (Exception exc)
        {
            assertTrue ( permissionDenied (exc) );
            Transaction.rollback();
        }
    }
}
