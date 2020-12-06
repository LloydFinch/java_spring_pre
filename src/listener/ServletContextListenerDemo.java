package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Name: ServletContextListenerDemo
 * Author: lloydfinch
 * Function: ServletContextListenerDemo
 * Date: 2020-09-10 17:28
 * Modify: lloydfinch 2020-09-10 17:28
 */
public class ServletContextListenerDemo implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        System.out.println("contextInitialized: " + servletContext.getServerInfo());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed");
    }
}
