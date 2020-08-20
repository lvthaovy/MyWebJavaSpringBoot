package org.demo.myshop.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * A Product.
 */
@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private Long id;

    @Column(name = "ProductName", nullable = false)
    private String name;

    @NotNull
    @Column(name = "Price", nullable = false)
    private Double price;


    @Column(name = "ImageURL")
    private String imageUrl;

    @Column(name = "CategoryID")
    private Long categID;

//    @ManyToOne
//    @JoinColumn(name = "CategoryID", nullable=false)
//    private Category category;
//
//    public Category getCategory(){
//        return category;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product name(String name) {
        this.name = name;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Product price(Double price) {
        this.price = price;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Product imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Long getCategID() {
        return categID;
    }

    public void setCategID(Long categID) {
        this.categID = categID;
    }

    public Product categID(Long categID){
        this.categID = categID;
        return this;
    }

    //    public Set<Category> getCategories() {
//        return categories;
//    }
//
//    public void setCategories(Set<Category> categories) {
//        this.categories = categories;
//    }
//
//    public Product categories(Set<Category> categories) {
//        this.categories = categories;
//        return this;
//    }
//
//    public Product addCategory(Category category) {
//        this.categories.add(category);
//        category.getProducts().add(this);
//        return this;
//    }
//
//    public Product removeCategory(Category category) {
//        this.categories.remove(category);
//        category.getProducts().remove(this);
//        return this;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }
//
//    @Override
//    public int hashCode() {
//        return 31;
//    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", price=" + getPrice() +
//                ", description='" + getDescription() + "'" +
//                ", unit='" + getUnit() + "'" +
                ", imageUrl='" + getImageUrl() + "'" +
//                ", imageBase64='" + getImageBase64() + "'" +
                "}";
    }
}
