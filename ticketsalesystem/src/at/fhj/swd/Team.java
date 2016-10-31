package at.fhj.swd;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NUC on 29.10.2016.
 */
@Entity
public class Team {
    @Id @Column(name = "id")
    private Integer id;
    private String name;

//    TODO: warum hier nicht ???
//    @ManyToMany
//    List<Match> matches = new ArrayList<Match>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (id != null ? !id.equals(team.id) : team.id != null) return false;
        if (name != null ? !name.equals(team.name) : team.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
