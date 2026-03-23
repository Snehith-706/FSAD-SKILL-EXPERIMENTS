package com.example.dao;

import com.example.entity.Product;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class ProductDAO {

    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public void saveProduct(Product product) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(product);
        tx.commit();
        session.close();
    }
}
