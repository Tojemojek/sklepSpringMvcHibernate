package pl.sda.javawwa.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/login.jsp").forward(req, resp);
        LOG.trace("Przekierowano do login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userPassword");
        HttpSession session = req.getSession();

        if ("test".equals(userName) && "test".equals(userPassword)) {
            session.setAttribute("user", userName);
            String previousUrl = (String) session.getAttribute("requestedUrl");

            if (previousUrl == null) {
                previousUrl = "zabezpieczone/calendar";
            }
            session.removeAttribute("requestedUrl");
            resp.sendRedirect(previousUrl);
            return;
        }

        req.setAttribute("oldUser", userName);
        req.setAttribute("error", "INCORRECT_CREDENTIALS");
        req.getRequestDispatcher("views/login.jsp").forward(req, resp);
        LOG.info("Przekierowano do login po niepoprawnym logowaniu");

    }
}
