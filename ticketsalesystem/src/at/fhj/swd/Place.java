package at.fhj.swd;

import javax.persistence.*;

/**
 * Created by NUC on 29.10.2016.
 */
@Entity
public class Place {
    @SequenceGenerator( name="PlaceIdGenerator",
            sequenceName="Place_Sequence",
            allocationSize = 1)

    @Id @GeneratedValue(generator="PlaceIdGenerator")
    private Integer id;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne
    @JoinColumn(name="fk_category_description")
    private Category category;

    public Place(Category category) {
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
