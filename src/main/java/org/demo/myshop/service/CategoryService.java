package org.demo.myshop.service;

import org.demo.myshop.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Iterable<Category> findAll();

    List<Category> search(String term);

    Category findOne(Long id);

    void save(Category category);

    void delete(Long id);

    Optional<Category> findById(Long id);

}
