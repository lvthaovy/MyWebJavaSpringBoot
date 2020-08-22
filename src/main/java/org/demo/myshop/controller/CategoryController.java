package org.demo.myshop.controller;

import org.demo.myshop.entity.Category;
import org.demo.myshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> findAllCategory() {
        List<Category> categories = (List<Category>) categoryService.findAll();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @RequestMapping(value = "/categories/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> getCategoryById(
            @PathVariable("id") Long id) {
        Optional<Category> category = categoryService.findById(id);

        if (!category.isPresent()) {
            return new ResponseEntity<>(category.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(category.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/categories",
            method = RequestMethod.POST)
    public ResponseEntity<Category> createCategory(
            @RequestBody Category category,
            UriComponentsBuilder builder) {
        categoryService.save(category);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/categories/{id}")
                .buildAndExpand(category.getId()).toUri());
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/categories/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Category> updateCategory(
            @PathVariable("id") Long id,
            @RequestBody Category category) {
        Optional<Category> currentCateg = categoryService
                .findById(id);

        if (!currentCateg.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentCateg.get().setName(category.getName());

        categoryService.save(currentCateg.get());
        return new ResponseEntity<>(currentCateg.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/categories/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Category> deleteCategory(
            @PathVariable("id") Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (!category.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
