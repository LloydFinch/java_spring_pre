package servlet;

import listener.AsyncListenerDemo;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Name: ASyncServletDemo
 * Author: lloydfinch
 * Function: ASyncServletDemo
 * Date: 2020-09-16 14:44
 * Modify: lloydfinch 2020-09-16 14:44
 */
@WebServlet(name = "AsyncServlet", urlPatterns = "/async", asyncSupported = true)
public class ASyncServletDemo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        println("doGet in AsyncServlet, current Thread is " + Thread.currentThread().getName());

        AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(5000);

        //add AsyncListener
        asyncContext.addListener(new AsyncListenerDemo());

        println("before do async task");
        asyncContext.start(() -> {
            println("start do async task: " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
                asyncContext.complete();
                println("finish async task");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        println("after do async task");

    }

    private void println(String str) {
        System.out.println(str);
    }
}
