package org.demo.myshop.service;

import org.demo.myshop.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Iterable<Product> findAll();

    List<Product> search(String term);

    Product findOne(Long id);

    void save(Product product);

    void delete(Long id);

    Optional<Product> findById(Long id);
}
