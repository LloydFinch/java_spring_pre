package filter;

import javax.print.DocFlavor;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Name: LoggerFilger
 * Author: lloydfinch
 * Function: LoggerFilger, use to record logger
 * Date: 2020-09-11 11:45
 * Modify: lloydfinch 2020-09-11 11:45
 */
@WebFilter(filterName = "LoggerFilter", urlPatterns = "/*", initParams = {
        @WebInitParam(name = "file_name", value = "log.txt"),
        @WebInitParam(name = "prefix", value = "URI: ")
}, asyncSupported = true)
public class LoggerFilter extends HttpFilter {

    private PrintWriter writer;
    private String prefix;

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init logger filter");
        FilterConfig config = getFilterConfig();

        //get prefix
        prefix = config.getInitParameter("prefix");

        //create logfile
        String fileName = config.getInitParameter("file_name");
        String path = config.getServletContext().getRealPath("/");
        File log = new File(path, fileName);
        System.out.println("create file: " + log.getAbsolutePath());
        try {
            writer = new PrintWriter(log);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }
    }

    @Override
    public void destroy() {
        System.out.println("destroy logger filter");
        if (writer != null) {
            writer.close();
        }
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("do logger filter");

        String uri = request.getRequestURI();
        String content = new Date() + " " + prefix + uri;
        System.out.println("write content: " + content);
        writer.println(content);
        writer.flush();

        chain.doFilter(request, response);
    }
}
