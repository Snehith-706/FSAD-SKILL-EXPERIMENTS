package com.example;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import com.example.entity.Product;
import java.util.List;

public class HQLApp {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Insert sample data
        session.save(new Product("Apple", "Fruit", 100, 10));
        session.save(new Product("Banana", "Fruit", 50, 20));
        session.save(new Product("Phone", "Electronics", 20000, 5));
        session.save(new Product("Laptop", "Electronics", 70000, 3));
        session.save(new Product("Pen", "Stationery", 10, 100));
        session.save(new Product("Notebook", "Stationery", 50, 60));

        tx.commit();

        // Sorting
        List<?> asc = session.createQuery("from Product order by price asc").list();
        List<?> desc = session.createQuery("from Product order by price desc").list();
        List<?> qty = session.createQuery("from Product order by quantity desc").list();

        // Pagination
        Query<?> q1 = session.createQuery("from Product");
        q1.setFirstResult(0);
        q1.setMaxResults(3);
        List<?> page1 = q1.list();

        Query<?> q2 = session.createQuery("from Product");
        q2.setFirstResult(3);
        q2.setMaxResults(3);
        List<?> page2 = q2.list();

        // Aggregate
        Long total = (Long) session.createQuery("select count(*) from Product").uniqueResult();
        Long available = (Long) session.createQuery("select count(*) from Product where quantity > 0").uniqueResult();
        Object[] minMax = (Object[]) session.createQuery("select min(price), max(price) from Product").uniqueResult();

        List<?> group = session.createQuery("select description, count(*) from Product group by description").list();

        // WHERE
        List<?> range = session.createQuery("from Product where price between 50 and 50000").list();

        // LIKE
        List<?> start = session.createQuery("from Product where name like 'A%'").list();
        List<?> end = session.createQuery("from Product where name like '%e'").list();
        List<?> contain = session.createQuery("from Product where name like '%one%'").list();
        List<?> length = session.createQuery("from Product where length(name)=5").list();

        System.out.println("Total: " + total);
        System.out.println("Available: " + available);
        System.out.println("Min Price: " + minMax[0]);
        System.out.println("Max Price: " + minMax[1]);

        session.close();
    }
}
