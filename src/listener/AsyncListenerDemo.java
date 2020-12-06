package listener;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import java.io.IOException;

/**
 * Name: AsyncListener
 * Author: lloydfinch
 * Function: AsyncListener
 * Date: 2020-09-16 17:31
 * Modify: lloydfinch 2020-09-16 17:31
 */
public class AsyncListenerDemo implements AsyncListener {
    @Override
    public void onComplete(AsyncEvent asyncEvent) throws IOException {
        println("onComplete: " + asyncEvent.toString());
    }

    @Override
    public void onTimeout(AsyncEvent asyncEvent) throws IOException {
        println("onTimeout: " + asyncEvent.toString());
    }

    @Override
    public void onError(AsyncEvent asyncEvent) throws IOException {
        println("onError: " + asyncEvent.toString());
    }

    @Override
    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
        println("onStartAsync: " + asyncEvent.toString());
    }

    private void println(String text) {
        System.out.println(text);
    }
}
