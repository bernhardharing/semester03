package at.fhj.swd;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NUC on 29.10.2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Category.findAll",
                query = "SELECT c FROM Category c"),
//        @NamedQuery(name = "Category.findByBezeichnung",
//                query = "SELECT a FROM Artikelstamm a WHERE a.bezeichnung = :bezeichnung")
})
@Table(name = "category")
public class Category {
    @Id
    private String description;
    private Integer price;


//TODO: Ask Teacher
//    @OneToMany
//    List<Place> place = new ArrayList<Place>();


    public Category(String description, Integer price) {
        this.description = description;
        this.price = price;
    }

    public Category() {
    }


    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (description != null ? !description.equals(category.description) : category.description != null)
            return false;
        if (price != null ? !price.equals(category.price) : category.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
