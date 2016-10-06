/*
 * project    company
 * subproject security
*/

package company.security.domain;

import static org.junit.Assert.*;
import        org.junit.BeforeClass;
import        org.junit.AfterClass;
import        org.junit.Test;

import  spize.persistence.Transaction;
import  spize.persistence.config.PersistenceUnitProperties;

import  company.queries.domain.FullTimeEmployee;

import  company.queries.domain.EmployeeRepository;
import  company.queries.domain.ProjectRepository;
import  company.queries.domain.DepartmentRepository;
import  company.queries.domain.ParkingSpaceRepository;


import  company.persistence.Persistence;

public class CompanyReaderTest extends CompanyTest
{

    final static String user     = "homer";
    final static String password = "donut";


	@BeforeClass public static void setup()
	{

        System.out.println (
            "Connecting to " + PersistenceUnitProperties.getUrl ());

        Persistence.connect (user, password);

        empRepository    = new EmployeeRepository ();
        projRepository   = new ProjectRepository();
        deptRepository   = new DepartmentRepository();
        pSpaceRepository = new ParkingSpaceRepository();
    }

	@AfterClass public static void teardown()
	{
        Persistence.close ();
    }


    @Test public void create ()
    {
        Transaction.begin();

        try
        {
            empRepository.create ( "must", "fail", 0);
            Transaction.commit();

            fail ();
        }
        catch (Exception exc)
        {
            System.out.println (exc);

            assertTrue (permissionDenied (exc));
            Transaction.rollback();
        }
    }

    @Test public void modify ()
    {

        Transaction.begin();

        FullTimeEmployee fte_1003 = (FullTimeEmployee)
                                        empRepository.find (emp1003_firstName
                                                           ,emp1003_lastName );
        assertTrue (fte_1003.getId() != 0);

        fte_1003.setSalary ( 0 );

        try
        {   Transaction.commit();

            fail ();
        }
        catch (Exception exc)
        {
            assertTrue (permissionDenied (exc));
        }
    }
}
