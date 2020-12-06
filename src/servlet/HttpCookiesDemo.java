package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Name: HttpCookiesDemo
 * Author: lloydfinch
 * Function: HttpCookiesDemo
 * Date: 2020-09-10 13:51
 * Modify: lloydfinch 2020-09-10 13:51
 */
@WebServlet(name = "HttpCookiesDemo", urlPatterns = {"/cookies"})
public class HttpCookiesDemo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addCookie(new Cookie("lan", "en"));
        Cookie cookie = new Cookie("loc", "china");
        cookie.setMaxAge(100);
        cookie.setSecure(true);
        cookie.setValue("ar");

        Cookie[] cookies = req.getCookies();
        for (Cookie cki : cookies) {
            System.out.println(cki.getName() + " : " + cki.getValue());
        }
    }
}
