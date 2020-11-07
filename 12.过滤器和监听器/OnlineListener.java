package com.xxxx.filter;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 在线人数统计
 *  当有新的Session对象创建时，人数+1
 *  当有session对象销毁时，人数-1
 */
@WebListener
public class OnlineListener implements HttpSessionListener {

    //定义在线人数
    private Integer onlineNumber = 0;

    /**
     * 当有新的Session对象创建时，人数+1
     * @param se
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //人数+1
        onlineNumber++;
//        //将人数设置到session作用域中
//        se.getSession().setAttribute("onlineNumber",onlineNumber);

        //将人数设置到servletContext作用域中
        se.getSession().getServletContext().setAttribute("onlineNumber",onlineNumber);
    }

    /**
     * 当有session对象销毁时，人数-1
     * @param se
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        //人数-1
        onlineNumber--;
        //将人数设置到servletContext作用域中
        se.getSession().getServletContext().setAttribute("onlineNumber",onlineNumber);
    }
}
