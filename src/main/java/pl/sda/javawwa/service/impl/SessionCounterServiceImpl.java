package pl.sda.javawwa.service.impl;

import org.springframework.stereotype.Service;
import pl.sda.javawwa.service.SessionCounterService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class SessionCounterServiceImpl implements SessionCounterService {

    String myCookieName = "myCounterInCookie";
    String mySesstionAtributeName = "myCounterInSession";



    public int incrementNumberInsideCookie(HttpServletRequest req, HttpServletResponse resp){
        Cookie myCookie = new Cookie(myCookieName, "1");
        Cookie[] cookies = req.getCookies();
        Integer licznik = null;
        for (Cookie c : cookies) {
            if (c.getName().equals(myCookieName)) {
                licznik = Integer.valueOf(c.getValue());
                licznik++;
                myCookie = new Cookie(myCookieName,licznik.toString());
                break;
            }
        }
        resp.addCookie(myCookie);

        return licznik;
    };

    public int incrementNumberInsideSession(HttpServletRequest req, HttpServletResponse resp){

        HttpSession session = req.getSession();

        Integer counter = (Integer) session.getAttribute(mySesstionAtributeName);

        if (counter == null) {
            counter = 1;
            session.setAttribute(mySesstionAtributeName, counter);
        } else{
            counter++;
            session.setAttribute(mySesstionAtributeName, counter);
        }

        return counter;
    };


}
