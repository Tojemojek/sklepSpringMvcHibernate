package pl.sda.javawwa.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class SessionServlet extends HttpServlet {
    String myCookieName = "myCounterInCookie";
    String mySesstionAtributeName = "myCounterInSession";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        incrementNumberInsideCookie(req, resp);
        incrementNumberInsideSession(req, resp);

    }

    private void incrementNumberInsideSession(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{

        HttpSession session = req.getSession();

        Integer counter = (Integer) session.getAttribute(mySesstionAtributeName);

        if (counter == null) {
            counter = 1;
            session.setAttribute(mySesstionAtributeName, counter);
        } else{
            counter++;
            session.setAttribute(mySesstionAtributeName, counter);
        }
        resp.getWriter().write(String.format("To jest %d numer w sesji\n",counter));
    }

    private void incrementNumberInsideCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
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
        resp.getWriter().write(String.format("To jest %d numer w ciastku\n",licznik));
    }
}
