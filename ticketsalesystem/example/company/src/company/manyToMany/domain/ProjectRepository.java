/*
 * project    company
 * subproject manyToMany
*/

package company.manyToMany.domain;

import spize.persistence.Persistence;

public class ProjectRepository extends    company.persistence.Repository <Project>
                               implements company.persistence.IRepository<Project>
{

    public  ProjectRepository ()
    {
        super ( Project.class) ;
    }

    public Project create (String name)
    {
        Project proj = new Project (name);

        entityManager.persist(proj);

        return proj;
    }

    // friend company.manyToManyAttributed.ProjectRepository !
    public static void reset ()
    {
		Persistence.resetTable    (schema, junctionTable);
		Persistence.resetTable    (schema, table);
		Persistence.resetSequence (schema, sequence);
	}

    static final String schema           = EmployeeRepository.schema;
    static final String table            = "project";
    static final String sequence         = "project_sequence";
    static final String junctionTable    = EmployeeRepository.junctionTable;

}
