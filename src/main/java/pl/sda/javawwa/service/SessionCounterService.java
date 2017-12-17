package pl.sda.javawwa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SessionCounterService {

    public int incrementNumberInsideCookie(HttpServletRequest req, HttpServletResponse resp);

    public int incrementNumberInsideSession(HttpServletRequest req, HttpServletResponse resp);

}
