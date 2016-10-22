package java.org.se.lab;

import java.util.List;

/**
 * Created by NUC on 22.10.2016.
 */
public class User implements UserTable{

    private int id;

    public User() {
    }

    @Override
    public void insertUser() {

    }

    @Override
    public User findById() {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    public int getId() {
        return id;
    }
}
