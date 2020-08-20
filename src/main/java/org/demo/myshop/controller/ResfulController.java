package org.demo.myshop.controller;

import org.demo.myshop.entity.Category;
import org.demo.myshop.entity.Product;
import org.demo.myshop.service.CategoryService;
import org.demo.myshop.service.ProductService;
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
public class ResfulController {

    private final ProductService productService;
    private final CategoryService categoryService;


    @Autowired
    public ResfulController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/products")
    public ResponseEntity<String> execute() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "no-cors");

        return ResponseEntity.ok()
                .headers(headers)
                .body("SUCCESS");
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAllProduct() {
        List<Product> products = (List<Product>) productService.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductById(
            @PathVariable("id") Long id) {
        Optional<Product> product = productService.findById(id);

        if (!product.isPresent()) {
            return new ResponseEntity<>(product.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/products",
            method = RequestMethod.POST)
    public ResponseEntity<Product> createProduct(
            @RequestBody Product product,
            UriComponentsBuilder builder) {
        productService.save(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/products/{id}")
                .buildAndExpand(product.getId()).toUri());
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/products/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(
            @PathVariable("id") Long id,
            @RequestBody Product product) {
        Optional<Product> currentProduct = productService
                .findById(id);

        if (!currentProduct.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentProduct.get().setName(product.getName());
        currentProduct.get().setPrice(product.getPrice());

        productService.save(currentProduct.get());
        return new ResponseEntity<>(currentProduct.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteProduct(
            @PathVariable("id") Long id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

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
