package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * Name: HttpSessionDemo
 * Author: lloydfinch
 * Function: HttpSessionDemo
 * Date: 2020-09-09 17:58
 * Modify: lloydfinch 2020-09-09 17:58
 */
@WebServlet(name = "HttpSessionDemo", urlPatterns = {"/session_demo", "session_uri"})
public class HttpSessionDemo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = req.getRequestURI();
        System.out.println("url: " + url);
        if (url.endsWith("session_uri")) {
            System.out.println("session_uri");
        }

        System.out.println("doGet");
        Enumeration<String> parameterNames = req.getParameterNames();
        Iterator<String> ite = parameterNames.asIterator();
        StringBuilder sb = new StringBuilder();
        while (ite.hasNext()) {
            String key = ite.next();
            String value = req.getParameter(key);
            sb.append(key)
                    .append(" = ")
                    .append(value)
                    .append("\n");
        }
        resp.setContentType("text/json");
        PrintWriter writer = resp.getWriter();
        writer.println(sb.toString());
        writer.flush();
        writer.close();


        //获取Session
        {
            HttpSession session = req.getSession(); //如果没有，则创建一个
            req.getSession(false);//如果没有，true就创建false则返回null
            session.setAttribute("case", "session");
            session.invalidate();//强制会话过期，并清空保存的数据

            session.setMaxInactiveInterval(30);//设置会话的过期时间为30s，若设置为0，则永不过期
        }
    }
}
