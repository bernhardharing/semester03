package at.fhj.swd;

import javax.persistence.*;

/**
 * Created by NUC on 13.10.2016.
 */
@Entity
@Table(name = "category", schema = "public", catalog = "haring")
public class CategoryEntity {
    @Id
    private String description;
    private Integer price;


    public CategoryEntity(String description, int price) {
        setDescription(description);
        setPrice(price);
    }

    public CategoryEntity() {
    }

    @Id
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    private void setPrice(Integer price) {
        this.price = price;
    }
}
