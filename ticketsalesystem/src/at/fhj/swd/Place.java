package at.fhj.swd;

import javax.persistence.*;

/**
 * Created by NUC on 29.10.2016.
 */
@Entity
public class Place {
    @Id
    private Integer id;

    @ManyToOne
    private Category category;

    @OneToOne
    private Ticket ticket;

    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
