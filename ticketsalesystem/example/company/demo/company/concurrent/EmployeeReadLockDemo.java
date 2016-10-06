/*
 * project    company
 * subproject concurrent
*/

package company.concurrent;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.LockModeType;
import javax.persistence.RollbackException;

import  company.concurrent.domain.Employee;

public class EmployeeReadLockDemo {

    private static EntityManagerFactory factory;
    private static EntityManager        manager;
    private static EntityTransaction    transaction;

    public static final String persistenceUnitName = "company";

    public static void main(String ... args)
    {

        if (args.length == 0)
        {
            System.out.println ( "Usage : { F[irst] | S [econd] }" );

    return;
        }

        final int       id           = 158;
        final int       raise        = 1111;

        boolean isFirst  = args[0].startsWith ("F");
        boolean isSecond = ! isFirst;

        Employee john;

        //  setup   ---------------------------------------------------

        factory = Persistence.createEntityManagerFactory( persistenceUnitName );
        assert factory != null;
        manager  = factory.createEntityManager();
        assert manager != null;
        transaction = manager.getTransaction();


        // find John   ------------------------------------------------

        transaction.begin ();

        john = manager.find (Employee.class, id, LockModeType.READ);

        assert john  != null;

        // update John   ----------------------------------------------

        john.raiseSalary (raise);

        System.out.println("salary raise = " + raise);

        // make sure the second user commits AFTER the first   --------

        if (isSecond)
            try
            {   Thread.sleep (  5 * 1000 );
            }
            catch (InterruptedException exc)
            {
                assert false;
            }

        if (isFirst)
        {   transaction.commit();
        }
        else
        {
            try
            {
                transaction.commit();

                assert false;
            }
            catch (RollbackException exc)
            {
                System.out.println ("expected  : " + exc);
            }
        }

        // cleanup : close the EM and EMF when done   -----------------
        manager.close();
        factory.close();

        System.out.println ("Passed   ---------------------------" );

    }
}
