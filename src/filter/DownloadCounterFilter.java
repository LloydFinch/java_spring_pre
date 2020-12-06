package filter;

import javax.servlet.FilterChain;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Name: DownloadCounterFilter
 * Author: lloydfinch
 * Function: DownloadCounterFilter
 * Date: 2020-09-11 13:58
 * Modify: lloydfinch 2020-09-11 13:58
 */
@WebFilter(filterName = "DownloadCounterFilter", urlPatterns = "/*", asyncSupported = true)
public class DownloadCounterFilter extends HttpFilter {
    private ExecutorService executor;
    private Properties properties;
    private File file;

    @Override
    public void init() throws ServletException {
        super.init();

        String path = getServletContext().getRealPath("/");
        String fileName = "down.txt";
        file = new File(path, fileName);
        if (!file.exists()) {
            try {
                boolean newFile = file.createNewFile();
                System.out.println("create new file: " + newFile);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServletException(e.getMessage());
            }
        }

        properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }

        executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void destroy() {
        executor.shutdown();
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        final String url = request.getRequestURI();
        executor.submit(() -> {
            String property = properties.getProperty("url");
            if (property == null) {
                properties.setProperty(url, "1");
            } else {
                int count = Integer.parseInt(property);
                properties.setProperty(url, String.valueOf(++count));
            }
            try {
                properties.store(new FileWriter(file), "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        chain.doFilter(request, response);
    }
}
