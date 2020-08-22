package org.demo.myshop.repository;

import org.demo.myshop.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findByNameContaining(String term);

    Optional<Category> findById(Long id);

}
