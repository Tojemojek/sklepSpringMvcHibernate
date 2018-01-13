package pl.sda.javawwa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.javawwa.dao.OrderDao;
import pl.sda.javawwa.dao.ProductDao;
import pl.sda.javawwa.dto.CustomerDto;
import pl.sda.javawwa.dto.OrderItemDto;
import pl.sda.javawwa.entity.Order;
import pl.sda.javawwa.entity.OrderItem;
import pl.sda.javawwa.entity.Product;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/zabezpieczone")
public class LoggedUserControler {

    private final String DEFAULT_PRODUCT_CATEGORY = "Beer";
    private final String DEFAULT_TOP_PRODUCTS = "10";

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    @RequestMapping("/")
    public ModelAndView orders(@RequestParam(name = "count", defaultValue = DEFAULT_TOP_PRODUCTS) Integer count,
                               @RequestParam(name = "showCategory",
                                       defaultValue = DEFAULT_PRODUCT_CATEGORY) String showCategory,
                               HttpSession session) {

        Map<String, Object> model = new HashMap<>();

        CustomerDto customerDto = (CustomerDto) session.getAttribute("user");
        Integer customerId = customerDto.getId();

        session.setAttribute("userID", customerId);
        List<Order> allOrdersForCustomersId = orderDao.getAllCustomersOrders(customerId);

        List<Integer> ordersID = new LinkedList<>();
        for (Order order : allOrdersForCustomersId) {
            ordersID.add(order.getId());
        }

        session.setAttribute("userOrders", ordersID);

        model.put("topProduct", productDao.getTopProducts(count));
        model.put("productCategories", productDao.getAllProductCategories());
        model.put("productInCategory", productDao.getProductsByCategory(showCategory));
        model.put("selectedProductType", showCategory);
        model.put("customersOrdersFromDatabase", allOrdersForCustomersId);
        return new ModelAndView("/index", model);
    }

    @RequestMapping("/orderDetails")
    public ModelAndView orderDetails(@RequestParam(name = "orderId", required = true)
                                             Integer orderId,
                                     HttpSession session) {

        Map<String, Object> model = new HashMap<>();

        List<Integer> userOrders = (List<Integer>) session.getAttribute("userOrders");

        if (!userOrders.contains(orderId)) {
            model.put("orderNumber", orderId);
            model.put("yourOrder", false);
        } else {
            Order orderById = orderDao.getOrderById(orderId);

            model.put("orderNumber", orderId);

            model.put("orderDetail", orderById.getOrderItems());

            BigDecimal totalCost = new BigDecimal("0.00").setScale(2);
            BigDecimal itemPrice;
            BigDecimal multiplier;

            if (orderById != null) {
                for (OrderItem oi : orderById.getOrderItems()) {
                    itemPrice = oi.getProduct().getPrice();
                    multiplier = new BigDecimal(oi.getQuantity()).setScale(2);
                    totalCost = multiplier.multiply(itemPrice).add(totalCost).setScale(2);
                }
            }
            model.put("totalCost", totalCost);
            model.put("yourOrder", true);
        }
        return new ModelAndView("zabezpieczone/orderDetails", model);
    }

    @RequestMapping("/basket")
    public ModelAndView basketDetails(@SessionAttribute(value = "basket", required = false) Map<Integer, OrderItemDto> basket) {

        Map<String, Object> model = new HashMap<>();
        Map<Integer, Product> productMap;
        List<OrderItem> orderItems = new LinkedList<>();

        if (basket == null) {
            model.put("productList", "");
        } else {
            productMap = productDao.getProductsByIdIn(basket.keySet());

            for (OrderItemDto orderItemDto : basket.values()) {
                Product product = productMap.get(orderItemDto.getProductId());
                Integer quantity = orderItemDto.getQuantity();
                orderItems.add(new OrderItem(null, product, quantity));
            }
        }

        BigDecimal totalCost = new BigDecimal("0.00").setScale(2);
        BigDecimal itemPrice;
        BigDecimal multiplier;

        if (orderItems != null) {
            for (OrderItem oi : orderItems) {
                itemPrice = oi.getProduct().getPrice();
                multiplier = new BigDecimal(oi.getQuantity()).setScale(2);
                totalCost = multiplier.multiply(itemPrice).add(totalCost).setScale(2);
            }
        }
        model.put("totalCost", totalCost);
        model.put("productList", orderItems);

        return new ModelAndView("zabezpieczone/basketDetails", model);
    }

}
