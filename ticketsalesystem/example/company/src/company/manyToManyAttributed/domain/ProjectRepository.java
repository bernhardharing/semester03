/*
 * project    company
 * subproject manyToMany-attributed
*/

package company.manyToManyAttributed.domain;


public class ProjectRepository extends    company.persistence.Repository <Project>
                               implements company.persistence.IRepository<Project>
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


    static void reset ()
    {
        company.manyToMany.domain.ProjectRepository.reset ();
	}


}
