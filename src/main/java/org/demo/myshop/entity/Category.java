package org.demo.myshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
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

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categories")
//    private Set<Product> products = new HashSet<>();
//
//    public Set<Product> getProducts() {
//        return products;
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

    public Category name(String name) {
        this.name = name;
        return this;
    }

//    public Set<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(Set<Product> products) {
//        this.products = products;
//    }
//
//    public Category products(Set<Product> products) {
//        this.products = products;
//        return this;
//    }
//
//    public Category addProduct(Product product) {
//        this.products.add(product);
//        product.getCategories().add(this);
//        return this;
//    }
//
//    public Category removeProduct(Product product) {
//        this.products.remove(product);
//        product.getCategories().remove(this);
//        return this;
//    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        return id != null && id.equals(((Category) o).id);
    }

//    @Override
//    public int hashCode() {
//        return 31;
//    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
//            ", description='" + getDescription() + "'" +
//            ", imageUrl='" + getImageUrl() + "'" +
//            ", imageBase64='" + getImageBase64() + "'" +
                "}";
    }
}
