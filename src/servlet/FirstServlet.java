package servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Name: FirstServlet
 * Author: lloydfinch
 * Function: FirstServlet
 * Date: 2020-08-28 17:52
 * Modify: lloydfinch 2020-08-28 17:52
 */
@WebServlet(name = "FirstServlet", urlPatterns = "/my")
public class FirstServlet extends HttpServlet {
    private ServletConfig servletConfig;

    @Override
    public void init() throws ServletException {
        super.init();
        servletConfig = getServletConfig();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");
        String name = servletConfig.getServletName();
        StringBuilder sb = new StringBuilder();
        sb.append("<html>")
                .append("<head>")
                .append("</head>")
                .append("<body>")
                .append("hello from ")
                .append(name)
                .append("</body>")
                .append("</html>");
        writer.write(sb.toString());
        writer.flush();
        writer.close();
    }
}
