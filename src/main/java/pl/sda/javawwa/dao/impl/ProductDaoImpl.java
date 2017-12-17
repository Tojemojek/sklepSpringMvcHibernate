package pl.sda.javawwa.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.sda.javawwa.dao.ProductDao;
import pl.sda.javawwa.entity.Product;

import java.util.List;


@Repository
public class ProductDaoImpl implements ProductDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> getTopProducts(int count) {
        Session session = sessionFactory.openSession();
        List<Product> products = session.createQuery("select p from Product p order by p.price asc", Product.class)
                .setMaxResults(count)
                .getResultList();
        session.close();
        return products;
    }
}