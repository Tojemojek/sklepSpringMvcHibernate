package pl.sda.javawwa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.javawwa.dao.ProductDao;
import pl.sda.javawwa.dto.BasketProductDto;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AnonymousUserControler {

    @Autowired
    private ProductDao productDao;

    @RequestMapping("/")
    public ModelAndView indexPage(@RequestParam(name = "count", defaultValue = "32") Integer count) {
        Map<String, Object> model = new HashMap<>();

        model.put("topProduct", productDao.getTopProducts(count));

        return new ModelAndView("index", model);

    }

    @RequestMapping("/addToBasket")
    public ModelAndView addToBasket(@RequestParam(name = "count", defaultValue = "32") Integer count,
                                    @RequestParam(name = "productId", required = true) Integer productId,
                                    HttpSession session) {

        Map<Integer, BasketProductDto> basketProductList = (Map<Integer, BasketProductDto>) session.getAttribute("basket");

        if (basketProductList == null) {
            basketProductList = new HashMap<>();
        }

        BasketProductDto product = basketProductList.get(productId);
        if (product == null){
            product = new BasketProductDto();
            product.setProductId(productId);
            product.setCount(1);
        } else{
            product.setProductId(product.getCount() +1);
        }

        session.setAttribute("basket", basketProductList);

        return new ModelAndView("redirect:/?count=" + count);
    }
}
