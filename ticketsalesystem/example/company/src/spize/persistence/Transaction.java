/*
 * library  spize
 * project  persistence
*/

package spize.persistence;

public class Transaction
{

    public static void begin ()
    {
        Persistence.getTransaction().begin ();
    }
    public static void commit ()
    {
        Persistence.getTransaction().commit ();
    }
    public static void rollback ()
    {
        Persistence.getTransaction().rollback ();
    }
}
