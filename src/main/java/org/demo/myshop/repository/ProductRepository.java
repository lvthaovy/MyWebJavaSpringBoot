package org.demo.myshop.repository;


import org.demo.myshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Product entity.
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByNameContaining(String term);

    Optional<Product> findById(Long id);

    List<Product> findByCategoryId(Long categID);

    @Query(value = "select distinct product from Product product left join fetch product.category",
            countQuery = "select count(distinct product) from Product product")
    Page<Product> findAllWithLazyRelationships(Pageable pageable);

    @Query("select distinct product from Product product left join fetch product.category")
    List<Product> findAllWithLazyRelationships();

    @Query("select product from Product product left join fetch product.category where product.id =:id")
    Optional<Product> findOneWithLazyRelationships(@Param("id") Long id);
}
