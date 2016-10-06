/*
 * project    company
 * subproject queries
*/

package company.queries.domain;

import java.util.List;

import javax.persistence.TypedQuery;

import company.queries.aggregate.DepartmentStatistics;

public class DepartmentRepository extends company.persistence.Repository <Department>
{

    public DepartmentRepository ()
    {
        super (Department.class);
    }


    public List<FullTimeEmployee> paidMoreThan (long minSalary, Department dept)
    {
        TypedQuery<FullTimeEmployee> query = entityManager.createNamedQuery (
                                            "FullTimeEmployee.paidMoreThanByDepartment"
                                            ,FullTimeEmployee.class );

        query.setParameter ("dept",      dept);
        query.setParameter ("minSalary", minSalary);


        return query.getResultList();
    }

    public FullTimeEmployee paidHighest (Department dept)
    {
        TypedQuery<FullTimeEmployee> query =
                        entityManager.createNamedQuery (
                                "FullTimeEmployee.paidHighestByDepartment"
                                ,FullTimeEmployee.class );

        query.setParameter ("dept",      dept);

        return query.getSingleResult();
    }

    public List<DepartmentStatistics> statistics ()
    {
        TypedQuery<DepartmentStatistics> query =
                        entityManager.createNamedQuery (
                                "Department.statistics"
                               ,DepartmentStatistics.class );

        return query.getResultList();
    }

    public List<Project> involvedIn (String departmentName)
    {

        TypedQuery<Project> query = entityManager.createNamedQuery (
                                            "Department.involvedInProjects"
                                            ,Project.class );

        query.setParameter("departmentName", departmentName);

        return query.getResultList ();
    }


    public Department create(String name) {
        Department dept = new Department(name);

        entityManager.persist(dept);

        return dept;
    }

    public Department find (String name)
    {
        TypedQuery<Department> query = entityManager.createNamedQuery (
                                            "Department.findByName"
                                            ,Department.class );

        query.setParameter ("name", name);

        return query.getSingleResult();
    }

    public List<Employee> findEmployees (String departmentName)
    {
        TypedQuery<Employee> query = entityManager.createNamedQuery (
                                            "Department.employees"
                                            ,Employee.class );

        query.setParameter ("name", departmentName);

        return query.getResultList();
    }

}
