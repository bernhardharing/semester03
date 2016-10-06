/*
 * project    company
 * subproject persistence
*/

package company.persistence;

import java.util.List;

public interface IRepository<T>
{

	T find (int id);

    List<T> findAll();

}

