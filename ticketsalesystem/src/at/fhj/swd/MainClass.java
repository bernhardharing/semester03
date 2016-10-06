package at.fhj.swd;

import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * Created by bernhard on 05.10.2016.
 */
@Entity
public class MainClass {

    @Id
    private int id;
    private String firstName;
    private String lastName;
    private long salary;


}
