package pl.sda.javawwa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.javawwa.dao.OrderDao;
import pl.sda.javawwa.dto.CustomerDto;
import pl.sda.javawwa.entity.Order;
import pl.sda.javawwa.entity.OrderItem;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/zabezpieczone")
public class LoggedUserControler {

    @Autowired
    private OrderDao orderDao;

    @RequestMapping("/")
    public ModelAndView orders(HttpSession session) {
        Map<String, Object> model = new HashMap<>();

        CustomerDto customerDto = (CustomerDto) session.getAttribute("user");
        Integer customerId = customerDto.getId();

        model.put("customersOrdersFromDatabase", orderDao.getAllCustomersOrders(customerId));

        return new ModelAndView("zabezpieczone/orders", model);
    }

    @RequestMapping("/orderDetails")
    public ModelAndView orderDetails(@RequestParam(name = "orderId", required = true) Integer orderId,
                                     HttpSession session) {

        Map<String, Object> model = new HashMap<>();

        Order orderItemsByOrderId = orderDao.getOrderById(orderId);

        model.put("orderDetail", orderItemsByOrderId.getOrderItems());
        model.put("orderNumber", orderId);

        BigDecimal totalCost = new BigDecimal("0.00").setScale(2);
        BigDecimal itemPrice = new BigDecimal("0.00").setScale(2);
        BigDecimal multiplier = new BigDecimal("0.00").setScale(2);

        if (orderItemsByOrderId != null) {
            for (OrderItem oi : orderItemsByOrderId.getOrderItems()) {
                itemPrice = oi.getProduct().getPrice();
                multiplier = new BigDecimal(oi.getQuantity()).setScale(2);
                totalCost = multiplier.multiply(itemPrice).add(totalCost).setScale(2);
            }
        }

        model.put("totalCost", totalCost);

        return new ModelAndView("zabezpieczone/orderDetails", model);
    }


    @RequestMapping("/logout")
    public ModelAndView logOut(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("requestedUrl");
        Integer count = (Integer) session.getAttribute("count");

        return new ModelAndView("redirect:/");
    }

}
