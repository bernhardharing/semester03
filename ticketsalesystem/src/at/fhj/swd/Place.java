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
    @JoinColumn(name="fk_category_description")
    private Category category;

//    @OneToOne
//    private Ticket ticket;

    public Place(Integer id, Category category) {
        this.id = id;
        this.category = category;

    }

    public Place() {
    }

    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
