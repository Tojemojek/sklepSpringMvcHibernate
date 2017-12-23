package pl.sda.javawwa.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.javawwa.dao.CustomerDao;
import pl.sda.javawwa.dao.OrderDao;
import pl.sda.javawwa.dao.ProductDao;
import pl.sda.javawwa.dao.impl.OrderDaoImpl;
import pl.sda.javawwa.dto.CustomerDto;
import pl.sda.javawwa.dto.OrderItemDto;
import pl.sda.javawwa.entity.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/zabezpieczone/basket/buy")
public class OrderAddController {


    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CustomerDao customerDao;

    @RequestMapping("")

    public ModelAndView createNewOrderFromBasket(HttpSession session) {

        CustomerDto customerDto = (CustomerDto) session.getAttribute("user");
        Customer customer = customerDao.findCustomerByEmail(customerDto.getEmail());

        LocalDateTime orderDate = LocalDateTime.now();

        Map<Integer, OrderItemDto> basketProductList = (Map<Integer, OrderItemDto>) session.getAttribute("basket");
        List<OrderItem> orderItems = new LinkedList<>();


        Order order = new Order(Status.NEW,orderDate, customer, null);

        if (basketProductList != null) {
            for (Map.Entry<Integer, OrderItemDto> orderItemDto : basketProductList.entrySet()) {
                Product product = productDao.getProductById(orderItemDto.getValue().getProductId());
                Integer quantity = orderItemDto.getValue().getQuantity();
                orderItems.add(new OrderItem(order, product, quantity));
            }
        }
        order.setOrderItems(orderItems);

        orderDao.addOrder(order);

        return new ModelAndView("redirect:/zabezpieczone/");
    }
}
