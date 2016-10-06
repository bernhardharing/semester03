/*
 * project    company
 * subproject concurrent
*/

package company.concurrent;

import java.util.Map;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.LockModeType;

import  company.concurrent.domain.Employee;

public class EmployeeWriteLockDemo {

    private static EntityManagerFactory factory;
    private static EntityManager        manager;
    private static EntityTransaction    transaction;

    public static final String persistenceUnitName = "company";

    static void reset ()
    {
        transaction.begin ();

        manager.createNativeQuery (
            "UPDATE concurrent.EMPLOYEE SET  SALARY = 10000, VERSION = 0")
                    .executeUpdate();

        transaction.commit ();
    }


    public static void main(String ... args)
    {

        if (args.length == 0)
        {
            System.out.println ( "Usage : { F[irst] | S [econd] }" );

    return;
        }

        final int       id           = 158;
        final int       salary       = 10000;
        final int       raise        = 1111;

        boolean isFirst  = args[0].startsWith ("F");
        boolean isSecond = ! isFirst;

        Employee john;

      //timeout > 0 not working (JPA 2.1, Postgres 9.3 :-( )
//      final int timeout_for_second_user =   0;      // nowait
        final int timeout_for_second_user = 500;      // wait in millisec

        //  setup   ---------------------------------------------------

        factory = Persistence.createEntityManagerFactory( persistenceUnitName );
        assert factory != null;
        manager  = factory.createEntityManager();
        assert manager != null;
        transaction = manager.getTransaction();

        if (isFirst)
        {
            reset ();
        }

        // make sure the second user locks  AFTER the first   --------

        if (isSecond)
        {
            System.out.println ( "waiting..." );

            try
            {   Thread.sleep (  3 * 1000 );
            }
            catch (InterruptedException exc)
            {
                assert false;
            }
        }

        // find John   ------------------------------------------------

        transaction.begin ();

        if ( isFirst || timeout_for_second_user < 0 )
        {
            System.out.println ( "about to set PESSIMISTIC_WRITE." );

            john = manager.find (Employee.class, id
                                 ,LockModeType.PESSIMISTIC_WRITE);
            System.out.println ( "PESSIMISTIC_WRITE  set." );
        }
        else
        {
            Map<String,Object> props = new HashMap<>();
            props.put("javax.persistence.lock.timeout", timeout_for_second_user);

            System.out.println ( "about to set PESSIMISTIC_WRITE; timeout = " + timeout_for_second_user);

            john = manager.find (Employee.class, id
                                 ,LockModeType.PESSIMISTIC_WRITE
                                 ,props );

            System.out.println ( "PESSIMISTIC_WRITE  set; timeout = " + timeout_for_second_user);
        }

        assert john  != null;

        if (isFirst)
        {
            assert john.getSalary () == salary;
        }

        // update John   ----------------------------------------------

        john.raiseSalary (raise);

        System.out.println("salary raise = " + raise);

        // make sure the first user takes her time before releasing the lock

        if (isFirst)
            try
            {   Thread.sleep ( 10 * 1000 );
            }
            catch (InterruptedException exc)
            {
                assert false;
            }

        transaction.commit();

        System.out.println("new salary = " + john.getSalary());

        if (isFirst)
             assert john.getSalary() == salary + raise;
        else assert john.getSalary() == salary + raise + raise;

        // cleanup : close the EM and EMF when done   -----------------
        manager.close();
        factory.close();

        System.out.println ("Passed   ---------------------------" );

    }
}
