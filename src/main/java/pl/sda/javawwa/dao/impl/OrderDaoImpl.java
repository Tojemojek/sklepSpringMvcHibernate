package pl.sda.javawwa.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.sda.javawwa.dao.OrderDao;
import pl.sda.javawwa.entity.Order;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    private static final Logger LOG = LoggerFactory.getLogger(OrderDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Order> getAllCustomersOrders(Integer customerId) {
        Session session = sessionFactory.openSession();
        List<Order> orders = session.createQuery(
                "select o from Order o where o.customer.id =:customerId order by id asc", Order.class)
                .setParameter("customerId", customerId)
                .getResultList();

        session.close();
        return orders;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Session session = sessionFactory.openSession();
        Order order;
        try {
            order = session.createQuery(
                    "select o from Order o left join fetch o.orderItems where o.id =:orderId", Order.class)
                    .setParameter("orderId", orderId)
                    .getSingleResult();

        } catch (NoResultException e) {
            LOG.debug("Nie znaleziono zamówienie o id" + orderId);
            return null;
        } finally {
            session.close();
        }
        LOG.debug("Znaleziono zamówienie o id" + orderId);
        return order;
    }

    @Override
    public Boolean addOrder(Order order) {
        Session session = sessionFactory.openSession();

        session.saveOrUpdate(order);
        session.close();

        if (order.getId() == null) {
            return true;
        }


        return false;
    }
}
