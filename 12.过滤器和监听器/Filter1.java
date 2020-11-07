package com.xxxx.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 过滤器
 *  1.@WebFilter("/s1")    配置拦截的资源路径
 *  2.doFilter()方法中需要设置放行，否则请求无法到达资源 （filterChain.doFilter(servletRequest, servletResponse);）
 *  3.如果是过滤器链，则先配置的先执行（首字母在前的先执行）；响应时，先配置的后执行
 */
@WebFilter("/s1")      //拦截路径是s1的资源
public class Filter1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter1 init");

    }

    /**
     * 过滤方法
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //doFilter放行方法前去做请求拦截的
        System.out.println("Filter1 正在拦截");
        //放行资源
        filterChain.doFilter(servletRequest, servletResponse);
        //doFilter放行方法后去做响应拦截的
        System.out.println("Filter1 处理响应");
    }

    @Override
    public void destroy() {
        System.out.println("Filter1 destroy");
    }
}