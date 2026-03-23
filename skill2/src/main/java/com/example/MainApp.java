package com.example;

import com.example.dao.ProductDAO;
import com.example.entity.Product;

public class MainApp {
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        dao.saveProduct(new Product("Laptop", "Gaming Laptop", 75000, 10));
        System.out.println("Inserted successfully");
    }
}
