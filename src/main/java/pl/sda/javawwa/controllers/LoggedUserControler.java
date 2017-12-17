package pl.sda.javawwa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/zabezpieczone")
public class LoggedUserControler {

    @RequestMapping("/")
    public ModelAndView orders(){
        Map<String, Object> model = new HashMap<>();

        //todo należy pobrać ze stworzonego dao wszystkie zamówienia dla użytkownika
        //którego dane są w sesji po kluczem user


        return new ModelAndView("zabezpieczone/orders",model);
    }

}
