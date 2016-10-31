package at.fhj.swd;

import javax.persistence.*;

/**
 * Created by NUC on 29.10.2016.
 */
@Entity
@Table(name = "with_track", schema = "public", catalog = "haring")
public class WithTrack extends Stadium{

    public WithTrack(Integer id, String location, Integer totalCapacity, Integer numberTracks) {
        super(id, location, totalCapacity);
        this.numberTracks = numberTracks;
    }

    public WithTrack() {
    }

    public Integer getNumberTracks() {
        return numberTracks;
    }

    public void setNumberTracks(Integer numberTracks) {
        this.numberTracks = numberTracks;
    }

    private Integer numberTracks;





}
