package pl.sda.javawwa.dao;

import pl.sda.javawwa.entity.Order;
import pl.sda.javawwa.entity.OrderItem;

import java.util.List;

public interface OrderDao {
    public List<Order> getAllCustomersOrders(Integer customerId);
    public Order getOrderById(Integer orderId);
}
