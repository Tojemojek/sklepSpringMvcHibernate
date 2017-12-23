package pl.sda.javawwa.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.sda.javawwa.dao.ProductDao;
import pl.sda.javawwa.entity.Product;

import javax.persistence.NoResultException;
import java.util.LinkedList;
import java.util.List;


@Repository
public class ProductDaoImpl implements ProductDao {

    private static final Logger LOG = LoggerFactory.getLogger(OrderDaoImpl.class);

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

    @Override
    public Product getProductById(int productId) {
        Session session = sessionFactory.openSession();
        Product product;
        try {
            product = session.createQuery(
                    "select p from Product p where p.id =:productId", Product.class)
                    .setParameter("productId", productId)
                    .getSingleResult();
        } catch (NoResultException e) {
            LOG.debug("Nie znaleziono zamówienie o id" + productId);
            return null;
        } finally {
            session.close();
        }
        LOG.debug("Znaleziono zamówienie o id" + productId);
        return product;
    }

    @Override
    public List<String> getAllProductCategories() {
        Session session = sessionFactory.openSession();

        List<String> categories = new LinkedList<>();

        categories = session.createQuery("select distinct p.category from Product p order by p.category asc").getResultList();
        session.close();

        return categories;

    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        Session session = sessionFactory.openSession();
        List<Product> products = session.createQuery("select p from Product p where p.category=:productCategory order by p.price asc", Product.class)
                .setParameter("productCategory", category)
                .getResultList();
        session.close();
        return products;
    }
}
