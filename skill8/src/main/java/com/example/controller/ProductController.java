package com.example.controller;

import com.example.entity.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repo;

    @PostConstruct
    public void init() {
        repo.save(new Product("Laptop", "Electronics", 70000));
        repo.save(new Product("Phone", "Electronics", 20000));
        repo.save(new Product("Shoes", "Fashion", 3000));
        repo.save(new Product("Watch", "Fashion", 5000));
        repo.save(new Product("Book", "Education", 500));
    }

    @GetMapping("/category/{category}")
    public List<Product> byCategory(@PathVariable String category) {
        return repo.findByCategory(category);
    }

    @GetMapping("/filter")
    public List<Product> byPrice(@RequestParam double min, @RequestParam double max) {
        return repo.findByPriceBetween(min, max);
    }

    @GetMapping("/sorted")
    public List<Product> sorted() {
        return repo.findAllSorted();
    }

    @GetMapping("/expensive/{price}")
    public List<Product> expensive(@PathVariable double price) {
        return repo.findExpensive(price);
    }
}
