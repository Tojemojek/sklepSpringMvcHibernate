package pl.sda.javawwa.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.javawwa.dto.DaysDto;
import pl.sda.javawwa.service.SessionCounterService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/zabezpieczone/old")
public class OldServletController {
    private static final Logger LOG = LoggerFactory.getLogger(OldServletController.class);

    @Autowired
    private SessionCounterService sessionCounterService;

    @RequestMapping("days")
    public ModelAndView getDaysList(@RequestParam(value = "days", required = false, defaultValue = "0") Integer days) {
        Map<String, Object> model = new HashMap<>();

        LocalDateTime dt = LocalDateTime.now();

        List<DaysDto> daysList = new LinkedList<>();
        for (int i = 1; i <= days; i++) {
            LocalDateTime dateAfter = dt.plusDays(i);
            DaysDto daysDto = new DaysDto();
            daysDto.setDateAfter(dateAfter);
            daysDto.setDaysAfter(i);
            daysList.add(daysDto);
        }
        model.put("daysList", daysList);
        model.put("daysParam", days);

        return new ModelAndView("days", model);
    }


    @RequestMapping("headers")
    public ModelAndView getAllHeaders(
            @RequestHeader Map<String, String> headers,
            @CookieValue("mojeCiacho") String cookie,
            HttpServletResponse resp) {

        Map<String, Object> model = new HashMap<>();

        model.put("headersMap", headers);

        resp.addCookie(new Cookie("mojeCiacho", "ciacho"));

        return new ModelAndView("headers", model);
    }

    @RequestMapping("counter")
    public ModelAndView getCountsFromCoocieAndSession(HttpServletRequest req, HttpServletResponse resp){

        Map<String, Object> model = new HashMap<>();
        int incrementNumberInsideCookie = sessionCounterService.incrementNumberInsideCookie(req, resp);
        int incrementNumberInsideSession = sessionCounterService.incrementNumberInsideSession(req, resp);

        model.put("cookieCounter",incrementNumberInsideCookie);
        model.put("sessionCounter",incrementNumberInsideCookie);

        return new ModelAndView("counters", model);
    }


}