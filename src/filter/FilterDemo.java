package filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Name: FilterDemo
 * Author: lloydfinch
 * Function: FilterDemo
 * Date: 2020-09-11 11:06
 * Modify: lloydfinch 2020-09-11 11:06
 */
@WebFilter(displayName = "FilterDemo", filterName = "FirstFilter", urlPatterns = {"*.png", "*.jpg", "*.gif"}, initParams = {
        @WebInitParam(name = "name", value = "android"),
        @WebInitParam(name = "age", value = "20")
}, asyncSupported = true)
public class FilterDemo extends HttpFilter {

    public FilterDemo() {
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init filter");
        FilterConfig filterConfig = getFilterConfig();
        String filterName = filterConfig.getFilterName();
        System.out.println("filter name: " + filterName);
        ServletContext servletContext = filterConfig.getServletContext();
        System.out.println("servlet context: " + servletContext);

        //API测试
        {
            Enumeration<String> initParameterNames = filterConfig.getInitParameterNames();
            Iterator<String> iterator = initParameterNames.asIterator();
            iterator.forEachRemaining(name -> {
                String value = filterConfig.getInitParameter(name);
                System.out.println(name + " = " + value);
            });
        }
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        super.doFilter(request, response, chain);
        System.out.println("do filter");
        String referrer = request.getHeader("referrer");
        System.out.println("referrer: " + referrer);
        if (referrer == null) {

        } else {

        }

        //proceed to next filter
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("destroy filter");
    }
}
