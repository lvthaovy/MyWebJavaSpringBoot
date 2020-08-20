package org.demo.myshop.repository;


import org.demo.myshop.entity.Product;
import org.springframework.data.repository.CrudRepository;
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
}
