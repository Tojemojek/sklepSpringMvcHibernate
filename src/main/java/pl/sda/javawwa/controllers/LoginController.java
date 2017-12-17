package pl.sda.javawwa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.javawwa.dao.CustomerDao;
import pl.sda.javawwa.dto.CustomerDto;
import pl.sda.javawwa.entity.Customer;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private CustomerDao customerDao;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginGet() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(name = "userEmail") String userEmail,
                              @RequestParam(name = "userPassword") String userPassword,
                              HttpSession session) {

        Map<String, Object> model = new HashMap<>();

        Customer customerByEmail = customerDao.findCustomerByEmail(userEmail);

        if (customerByEmail != null && customerByEmail.getPassword().equals(userPassword)) {

            session.setAttribute("user", new CustomerDto(customerByEmail));
            String previousUrl = (String) session.getAttribute("requestedUrl");

            if (previousUrl == null) {
                previousUrl = "zabezpieczone/";
            }
            session.removeAttribute("requestedUrl");
            return new ModelAndView("redirect:" + previousUrl);
        }
        model.put("error", "INCORRECT_CREDENTIALS");
        return new ModelAndView("login", model);

    }
}
