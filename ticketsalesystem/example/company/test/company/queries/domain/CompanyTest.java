/*
 * project    company
 * subproject queries
*/

package company.queries.domain;

import java.util.List;

import javax.persistence.NoResultException;

import static org.junit.Assert.*;
import        org.junit.BeforeClass;
import        org.junit.AfterClass;
import        org.junit.Test;


import spize.util.Printer;

import  company.queries.aggregate.ConciseEmployee;
import  company.queries.aggregate.DepartmentStatistics;

import  company.persistence.Persistence;

public class CompanyTest
{
    static final boolean verbose = true;

    static final String    emp1001_firstName   = "John";
    static final String    emp1001_lastName    = "Pot";

    static final String    emp1003_firstName   = "Peter";
    static final String    emp1003_lastName    = "Pan";
    static final String    emp1003_department  = "QA";
    static final long      emp1003_salary      = 50000;

    static final String    proj3001_name  = "Release1";

    static final int       pSpace9001_id       = 9001;

    static final String    dep_qa_name         = "QA";
    static final String    dep_qa_north_name   = "QANorth";


    static EmployeeRepository    empRepository;
    static ProjectRepository    projRepository;
    static DepartmentRepository deptRepository;
    static ParkingSpaceRepository pSpaceRepository;

    /* security uses our schema and might have left some stuff in our
     * tables; hence we have to call security's reset to get rid of it.
    */
    static void reset ()
    {
         company.security.domain.CompanyTest. reset (Persistence.getEntityManager());
    }

	@BeforeClass public static void setup()
    {
        empRepository = new EmployeeRepository();
        projRepository = new ProjectRepository();
        deptRepository = new DepartmentRepository();
        pSpaceRepository = new ParkingSpaceRepository();

        reset ();

    }

	@AfterClass public static void teardown()
	{
        Persistence.close ();       // close the EM and EMF when done
    }


    @Test public void find ()
    {

        // Employees   ------------------------------------------------

        Employee emp1 = empRepository.find (emp1001_firstName
                                           ,emp1001_lastName );

        assertEquals ( emp1001_firstName,  emp1.getFirstName());
        assertEquals ( emp1001_lastName,   emp1.getLastName());
        try {
            empRepository.find ("un", "known");

            fail ();
        }
        catch (NoResultException exc) {}


        long salary = empRepository.getSalary (  emp1003_firstName
                                                ,emp1003_lastName
                                                ,emp1003_department );

        assertEquals ( emp1003_salary, salary );

        try {
            salary = empRepository.getSalary (  emp1003_firstName
                                               ,emp1003_lastName
                                               ,"salzamt" );
            fail ();
        }
        catch (NoResultException exc) {}

        // parking space   --------------------------------------------

        List <ParkingSpace> pSpaces = pSpaceRepository.findAll ();
        assertEquals ( 4,  pSpaces.size ());

        ParkingSpace pSpace1 = pSpaceRepository.find (pSpace9001_id);
        assertEquals ( emp1, pSpace1.getEmployee());
    }

    @Test public void specialFinds ()
    {
        // emps for department   --------------------------------------
        List <Employee> empsInProj3001 = projRepository.findEmployees (proj3001_name);
        assertEquals ( 7,  empsInProj3001.size ());
        if (verbose) Printer.print (empsInProj3001, "emps working at project " + proj3001_name);

        // emp(s) without projects  -----------------------------------

        List <Employee> empsWithoutProj = empRepository.withoutProjects ();

        if (verbose) Printer.print (empsWithoutProj,  "emps without projects ");
        assertEquals ( 1, empsWithoutProj.size ());

        // emp(s) without pSpace    -----------------------------------

        List <Employee> empsWithoutSpace = empRepository.withoutParkingSpace ();

        if (verbose) Printer.print (empsWithoutSpace, "emps without parking space");
        assertEquals ( 9,  empsWithoutSpace.size ());

        // emos belonging to a department   ---------------------------

        List <Employee> emps_in_qa = deptRepository.findEmployees ("QA");

        if (verbose) Printer.print (emps_in_qa, "emps_in_qa");

        assertEquals ( 3, emps_in_qa.size ());


        // salary   ---------------------------------------------------

        Department qa = deptRepository.find ( dep_qa_name );

        List <FullTimeEmployee> fullEmps = deptRepository.
                                                paidMoreThan (10000, qa);

        assertEquals ( 1, fullEmps.size ());

        if (verbose) Printer.print (fullEmps, "emps in QA paid more than 10000" );

        FullTimeEmployee fullEmp = deptRepository.paidHighest (qa);

        assertEquals ( emp1003_salary, fullEmp.getSalary());

        // parking space   --------------------------------------------

        List <ParkingSpace> pSpace_free = pSpaceRepository.free ();
        if (verbose) Printer.print (pSpace_free, "free parking spaces");
        assertEquals ( 1, pSpace_free.size ());

    }

    @Test public void aggregates ()
    {
        List<ConciseEmployee>
            conciseEmployees = projRepository.findEmployeesAndShortenThem
                                                            (proj3001_name);

        if (verbose) Printer.print (conciseEmployees, "Concise List : all emps working on " + proj3001_name);

        assertEquals ( 7, conciseEmployees.size ());



        // department    ----------------------------------------------

        List<DepartmentStatistics> dep_stat =  deptRepository.statistics();
        if (verbose) Printer.print (dep_stat, "all departments statistics" );
        assertEquals ( 2, dep_stat.size ());

        List <Project> projs = deptRepository.involvedIn (dep_qa_north_name);
        if (verbose) Printer.print (projs, dep_qa_north_name + " is involved in projects : ");
        assertEquals ( 1, projs.size ());

    }

}
