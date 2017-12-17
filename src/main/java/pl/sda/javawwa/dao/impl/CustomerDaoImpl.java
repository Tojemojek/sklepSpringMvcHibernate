package pl.sda.javawwa.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.sda.javawwa.dao.CustomerDao;
import pl.sda.javawwa.entity.Customer;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {


    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getAllCustomers() {
        Session session = sessionFactory.openSession();
        List<Customer> allCustomers = session.createQuery("select c from Customer c", Customer.class).getResultList();
        session.close();
        return allCustomers;
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        Session session = sessionFactory.openSession();
        Customer customer;
        try {
            customer = session.createQuery("select c from Customer c where c.email = :email", Customer.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return customer;
        } catch (NoResultException e) {
            return null;
        } finally {
            session.close();
        }

    }
}
