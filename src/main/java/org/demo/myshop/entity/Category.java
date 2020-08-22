package org.demo.myshop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * A Category.
 */
@Entity
@Table(name = "categories")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    private Long id;

    @NotNull
    @Column(name = "CategoryName", nullable = false)
    private String name;

    //    @JsonManagedReference
    @OneToMany(mappedBy = "category")
//    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Product> products;

    @JsonBackReference
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

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



    @Override
    public String toString() {
        return "Category{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                "}";
    }
}
