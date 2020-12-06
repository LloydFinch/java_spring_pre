package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Name: ServletConfigDemo
 * Author: lloydfinch
 * Function: ServletConfigDemo
 * Date: 2020-09-08 11:22
 * Modify: lloydfinch 2020-09-08 11:22
 */
@WebServlet(name = "ServletConfigDemo", urlPatterns = "/servlet_demo", initParams = {
        @WebInitParam(name = "name", value = "admin"),
        @WebInitParam(name = "email", value = "admin@example.com")
})
public class ServletConfigDemo implements Servlet {

    private transient ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        ServletConfig config = this.servletConfig;
        String name = config.getInitParameter("name");
        String email = config.getInitParameter("email");

        servletResponse.setContentType("text/html");
        PrintWriter writer = servletResponse.getWriter();
        StringBuilder sb = new StringBuilder();
        sb.append("<html>")
                .append("<head>")
                .append("</head>")
                .append("<body>")
                .append("name: ").append(name)
                .append("<br/>")
                .append("email: ").append(email)
                .append("</body>")
                .append("</html>");
        writer.write(sb.toString());
        writer.flush();
        writer.close();
    }

    @Override
    public String getServletInfo() {
        return "ServletConfigDemo";
    }

    @Override
    public void destroy() {

    }
}
