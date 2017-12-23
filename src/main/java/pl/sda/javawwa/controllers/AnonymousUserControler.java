package pl.sda.javawwa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.javawwa.dao.ProductDao;
import pl.sda.javawwa.dto.CustomerDto;
import pl.sda.javawwa.dto.OrderItemDto;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AnonymousUserControler {
    private final String DEFAULT_PRODUCT_CATEGORY = "Beer";
    @Autowired
    private ProductDao productDao;

    @RequestMapping("/")
    public ModelAndView indexPage(@RequestParam(name = "count", defaultValue = "32") Integer count,
                                  @RequestParam(name = "showCategory",
                                          defaultValue = DEFAULT_PRODUCT_CATEGORY) String showCategory,
                                  HttpSession session) {


        Object user = session.getAttribute("user");
        if (user != null && (user instanceof CustomerDto)) {
            return new ModelAndView("redirect:/zabezpieczone/");
        }

        Map<String, Object> model = new HashMap<>();

        model.put("topProduct", productDao.getTopProducts(count));
        model.put("productCategories", productDao.getAllProductCategories());
        model.put("productInCategory", productDao.getProductsByCategory(showCategory));

        return new ModelAndView("index", model);

    }

    @RequestMapping("/addToBasket")
    public ModelAndView addToBasket(@RequestParam(name = "count", defaultValue = "32") Integer count,
                                    @RequestParam(name = "productId", required = true) Integer productId,
                                    @RequestParam(name = "quantity", required = true, defaultValue = "1") Integer orderedQuantity,
                                    HttpSession session) {

        Map<Integer, OrderItemDto> basketProductList = (Map<Integer, OrderItemDto>) session.getAttribute("basket");

        if (basketProductList == null) {
            basketProductList = new HashMap<>();
        }

        if (orderedQuantity < 0) {
            orderedQuantity = 0;
        }

        OrderItemDto product = basketProductList.get(productId);
        if (product == null) {
            product = new OrderItemDto();
            product.setProductId(productId);
            product.setQuantity(orderedQuantity);
            basketProductList.put(productId, product);
        } else {
            product.setQuantity(product.getQuantity() + orderedQuantity);
            basketProductList.replace(productId, product);
        }

        session.setAttribute("basket", basketProductList);

        return new ModelAndView("redirect:/?count=" + count);
    }
}
