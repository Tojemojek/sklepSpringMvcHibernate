package pl.sda.javawwa.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.sda.javawwa.dao.OrderDao;
import pl.sda.javawwa.dao.OrderItemDao;
import pl.sda.javawwa.entity.Order;
import pl.sda.javawwa.entity.OrderItem;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {

    private static final Logger LOG = LoggerFactory.getLogger(OrderItemDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addOrderItem(OrderItem orderItem) {
        Session session = sessionFactory.openSession();

        session.saveOrUpdate(orderItem);
        session.close();

    }
}
