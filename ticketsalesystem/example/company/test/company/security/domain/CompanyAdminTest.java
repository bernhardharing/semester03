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

import  company.queries.domain.FullTimeEmployee;

import  company.queries.domain.EmployeeRepository;
import  company.queries.domain.ProjectRepository;
import  company.queries.domain.DepartmentRepository;
import  company.queries.domain.ParkingSpaceRepository;

import  company.persistence.Persistence;

@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class CompanyAdminTest extends CompanyTest
{

    final static String user     = "burns";
    final static String password = "smithers";


	@BeforeClass public static void setup()
	{
        Persistence.connect (user, password);

        reset (Persistence.getEntityManager());

        empRepository = new EmployeeRepository ();
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

    @Test public void removeEntity ()
    {
        reset (Persistence.getEntityManager());
    }


    @Test public void createView ()
    {

        Transaction.begin();

        Persistence.getEntityManager().createNativeQuery(createViewStm).executeUpdate();

        Transaction.commit();
    }

}
