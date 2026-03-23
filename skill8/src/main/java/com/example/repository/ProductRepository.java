package com.example.repository;

import com.example.entity.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategory(String category);
    List<Product> findByPriceBetween(double min, double max);

    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> findAllSorted();

    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findExpensive(@Param("price") double price);

    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findByCategoryJPQL(@Param("category") String category);
}
