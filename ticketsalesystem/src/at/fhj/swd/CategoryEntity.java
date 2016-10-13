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

    protected CategoryEntity(){
    }
    protected CategoryEntity(String description, int price){
        setPrice(price);
        setDescription(description);
    }


    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
