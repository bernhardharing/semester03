package java.org.se.lab;

import java.util.List;

/**
 * Created by NUC on 22.10.2016.
 */
public interface UserTable {

    public  void insertUser();

    public User findById();

    public List<User> findAll();

}
