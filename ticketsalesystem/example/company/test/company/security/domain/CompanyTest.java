/*
 * project    company
 * subproject security
*/

package company.security.domain;

import java.util.List;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
import        org.junit.Test;

import  spize.persistence.Transaction;

import  company.queries.domain.Employee;
import  company.queries.domain.ParkingSpace;

import  company.queries.domain.EmployeeRepository;
import  company.queries.domain.ProjectRepository;
import  company.queries.domain.DepartmentRepository;
import  company.queries.domain.ParkingSpaceRepository;

import spize.util.Printer;

public abstract class CompanyTest
{
    protected static final boolean verbose = true;

    static EmployeeRepository        empRepository = null;
    static ProjectRepository        projRepository = null;
    static DepartmentRepository     deptRepository = null;
    static ParkingSpaceRepository pSpaceRepository = null;


    static final String    createViewStm   = "CREATE OR REPLACE VIEW testView AS SELECT * FROM EMPLOYEE";

    static final String    emp_new_firstName = "New";
    static final String    emp_new_lastName  = "Born";
    static final long      emp_new_salary    = 1;

    static final String    emp1001_firstName   = "John";
    static final String    emp1001_lastName    = "Pot";

    static final String    emp1002_firstName   = "Robert";
    static final String    emp1002_lastName    = "Dil";

    static final String    emp1003_firstName   = "Peter";
    static final String    emp1003_lastName    = "Pan";
    static final String    emp1003_department  = "QA";
    static final long      emp1003_salary      = 50000;

    static final String    proj3001_name  = "Release1";
    static final String    proj3002_name  = "Release2";

    static final String    emp1005_firstName   = "Scott";
    static final String    emp1005_lastName    = "Oaks";
    static final long      emp1005_salary      = 60000;

    static final int       pSpace9001_id       = 9001;

    static final String    dep_qa_name         = "QA";
    static final String    dep_qa_north_name   = "QANorth";



    static boolean permissionDenied (Exception exc)
    {

        System.out.println (exc.getMessage());

        return    exc.getMessage().contains ("permission")
               && exc.getMessage().contains ("denied");

    }

    @Test public void find ()
    {
        List <Employee> emps = null;

        empRepository.find (emp1001_firstName
                           ,emp1001_lastName );

        long salary = empRepository.getSalary (  emp1003_firstName
                                                ,emp1003_lastName
                                                ,emp1003_department );

        assertEquals (emp1003_salary, salary);

        emps = projRepository.findEmployees (proj3001_name);
        assertEquals ( 7, emps.size ());
        if (verbose) Printer.print (emps, "emps working at project " + proj3001_name);

        emps = empRepository.withoutProjects ();

        if (verbose) Printer.print (emps,  "emps without projects ");
        // "Lizzy Lazy" and - only for WRITER and ADMIN - "New Born"
        assertTrue ( emps.size () >= 1);
        assertTrue ( emps.size () <= 2);

        emps = empRepository.withoutParkingSpace ();

        if (verbose) Printer.print (emps, "emps without parking space");
        // existing emps and - only for WRITER and ADMIN - "New Born" :
        assertTrue ( emps.size () >=  9);
        assertTrue ( emps.size () <= 10);

        deptRepository.find ( dep_qa_name );

        List <ParkingSpace> pSpaces = pSpaceRepository.findAll ();
        assertEquals ( 4, pSpaces.size ());
    }

    public static void reset (EntityManager em)
    {
        Transaction.begin();

        em.createQuery (
            "DELETE FROM FullTimeEmployee fte WHERE fte.lastName = '"
            + emp_new_lastName + "'")
                    .executeUpdate();

        Transaction.commit();
    }

}
