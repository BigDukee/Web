package com.xxxx.filter;


import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class Listener1 implements HttpSessionListener {
    /**
     * 当session对象被创建时执行
     * @param se
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session对象被创建");
    }

    /**
     * 当session对象被销毁时执行
     * @param se
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session被销毁了");
    }
}
