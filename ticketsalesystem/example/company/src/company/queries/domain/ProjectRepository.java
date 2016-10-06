/*
 * project    company
 * subproject queries
*/

package company.queries.domain;

import java.util.List;

import javax.persistence.TypedQuery;

import company.queries.aggregate.ConciseEmployee;

public class ProjectRepository extends company.persistence.Repository <Project>
{

    public ProjectRepository()
    {
        super (Project.class);
    }

    public Project create (String name)
    {
        Project proj = new Project (name);

        entityManager.persist(proj);

        return proj;
    }

    public List<Employee> findEmployees(String projectName)
    {

        TypedQuery<Employee> query = entityManager.createNamedQuery (
                                            "Project.employees"
                                            ,Employee.class );

        query.setParameter("projectName", projectName);

        return query.getResultList ();
    }



    public List<ConciseEmployee>
                findEmployeesAndShortenThem (String projectName)
    {

        TypedQuery<ConciseEmployee> query =
                    entityManager.createNamedQuery (
                            "Project.employees_concise"
                            ,ConciseEmployee.class );

        query.setParameter("projectName", projectName);

        return query.getResultList ();


    }


}
