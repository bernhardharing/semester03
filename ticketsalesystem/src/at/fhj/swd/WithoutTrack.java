package at.fhj.swd;

import javax.persistence.*;

/**
 * Created by NUC on 29.10.2016.
 */
@Entity
@Table(name = "without_track", schema = "public", catalog = "haring")
public class WithoutTrack extends Stadium {


    public WithoutTrack(Integer id, String location, Integer totalCapacity) {
        super(id, location, totalCapacity);
    }


    public WithoutTrack() {
    }
}
